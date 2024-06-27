package com.examples.moviesapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.examples.moviesapp.app.MovieApplication
import com.examples.moviesapp.databinding.FragmentHomePageBinding
import com.examples.moviesapp.presentation.recyclers.adapters.MovieAdapter
import com.examples.moviesapp.presentation.states.HomePageState
import com.examples.moviesapp.presentation.viewmodels.HomePageViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MyLog"
class HomePageFragment : Fragment() {

    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!
    @Inject lateinit var viewModel: HomePageViewModel
    @Inject lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity?.applicationContext as MovieApplication).appComponent.inject(this)
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.movieBlock.setAdapter(adapter)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {

                viewModel.state.collect { state ->
                    when(state) {
                        HomePageState.Loading -> {

                        }
                        HomePageState.Success -> {
                            Log.d(TAG, "movieList = ${viewModel.movieList?.items}")
                            viewModel.movieList?.items?.let { adapter.setData(it) }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}