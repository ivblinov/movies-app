package com.examples.moviesapp.presentation.screens.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.examples.moviesapp.R
import com.examples.moviesapp.databinding.FragmentListBinding
import com.examples.moviesapp.presentation.screens.list.recycler.ListAdapter
import com.examples.moviesapp.presentation.screens.list.recycler.ListItemDecoration
import com.examples.moviesapp.presentation.states.State
import com.examples.moviesapp.utils.appComponent
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: ListViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listRV.adapter = ListAdapter(onClick = ::navigateToFilm)

        val spacing = resources.getDimensionPixelSize(R.dimen.space_between_item)
        binding.listRV.addItemDecoration(ListItemDecoration(2, spacing, false))

        subject()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun inject() {
        requireContext().appComponent().inject(this)
    }

    private fun navigateToFilm(filmId: Int) = viewModel.navigateToFilm(filmId)

    private fun subject() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    State.Loading -> {}
                    State.Success -> {
                        viewModel.premierList?.items?.let { getListAdapter()?.updateList(it) }
                    }
                }
            }
        }
    }

    private fun getListAdapter() = binding.listRV.adapter as? ListAdapter
}