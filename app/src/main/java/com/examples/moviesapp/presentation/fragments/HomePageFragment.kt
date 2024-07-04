package com.examples.moviesapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.examples.moviesapp.app.MovieApplication
import com.examples.moviesapp.databinding.FragmentHomePageBinding
import com.examples.moviesapp.presentation.recyclers.adapters.CollectionsAdapter
import com.examples.moviesapp.presentation.recyclers.adapters.DynamicAdapter
import com.examples.moviesapp.presentation.recyclers.adapters.MovieAdapter
import com.examples.moviesapp.presentation.states.AllButtonState
import com.examples.moviesapp.presentation.states.HomePageState
import com.examples.moviesapp.presentation.viewmodels.HomePageViewModel
import com.examples.moviesapp.setVisible
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomePageFragment : Fragment() {

    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: HomePageViewModel

    @Inject
    lateinit var adapter: MovieAdapter

    @Inject
    lateinit var popularAdapter: CollectionsAdapter

    @Inject
    lateinit var top250Adapter: CollectionsAdapter

    @Inject
    lateinit var dynamicAdapter: DynamicAdapter

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

        binding.premieresBlock.setAdapter(adapter)
        binding.popularBlock.setAdapter(popularAdapter)
        binding.top250Block.setAdapter(top250Adapter)
        binding.dynamicSelectionBlock.setAdapter(dynamicAdapter)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch {
                    viewModel.premiereState.collect { state ->
                        when (state) {
                            HomePageState.Success -> {
                                viewModel.premiereList?.items?.let { adapter.setData(it) }
                            }

                            HomePageState.Loading -> {}
                        }
                    }
                }
                launch {
                    viewModel.allPremiereState.collect { premiereState ->
                        when (premiereState) {
                            AllButtonState.Visible ->
                                setVisible(binding.premieresBlock.additionalText)

                            else -> {}
                        }
                    }
                }
                launch {
                    viewModel.popularState.collect { state ->
                        when (state) {
                            HomePageState.Loading -> {}
                            HomePageState.Success -> {
                                viewModel.popularFilms?.items?.let { popularAdapter.setData(it) }
                            }
                        }
                    }
                }
                launch {
                    viewModel.allPopularState.collect { popularState ->
                        when (popularState) {
                            AllButtonState.Visible ->
                                setVisible(binding.popularBlock.additionalText)

                            else -> {}
                        }
                    }
                }
                launch {
                    viewModel.top250State.collect { state ->
                        when (state) {
                            HomePageState.Loading -> {}
                            HomePageState.Success -> {
                                viewModel.top250Films?.items?.let { top250Adapter.setData(it) }
                            }
                        }
                    }
                }
                launch {
                    viewModel.allTop250State.collect { top250State ->
                        when (top250State) {
                            AllButtonState.Visible -> setVisible(binding.top250Block.additionalText)
                            else -> {}
                        }
                    }
                }
                launch {
                    viewModel.dynamicSelectionState.collect { state ->
                        when (state) {
                            HomePageState.Loading -> {}
                            HomePageState.Success -> {
                                viewModel.dynamicSelectionFilms?.items?.let {
                                    dynamicAdapter.setData(it)
                                }
                                viewModel.dynamicSelectionFilms?.titleBlock?.let {
                                    binding.dynamicSelectionBlock.mainText?.text =
                                        it.replaceFirstChar { char ->
                                            char.uppercase()
                                        }
                                }
                            }
                        }
                    }
                }
                launch {
                    viewModel.allDynamicSelectionState.collect { dynamicSelectionState ->
                        when (dynamicSelectionState) {
                            AllButtonState.Visible -> setVisible(binding.dynamicSelectionBlock.additionalText)
                            else -> {}
                        }
                    }
                }
                launch {
                    viewModel.dynamicSelectionState.collect { state ->
                        when(state) {
                            HomePageState.Loading -> {  }
                            HomePageState.Success -> {
                                viewModel.dynamicSelectionFilms?.country?.country?.let { binding.dynamicSelectionBlock.mainText?.text = it }
                            }
                        }
                    }
                }
                launch {
                    viewModel.allDynamicSelectionState.collect { dynamicSelectionState ->
                        when(dynamicSelectionState) {
                            AllButtonState.Visible -> setVisible(binding.dynamicSelectionBlock.additionalText)
                            else -> { }
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