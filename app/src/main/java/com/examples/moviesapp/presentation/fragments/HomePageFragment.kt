package com.examples.moviesapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.examples.moviesapp.data.api.RetrofitInstance
import com.examples.moviesapp.databinding.FragmentHomePageBinding
import com.examples.moviesapp.presentation.states.HomePageState
import com.examples.moviesapp.presentation.viewmodels.HomePageViewModel
import kotlinx.coroutines.launch

private const val TAG = "MyLog"
class HomePageFragment : Fragment() {

    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomePageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {

                viewModel.state.collect { state ->
                    when(state) {
                        HomePageState.Loading -> {

                        }
                        HomePageState.Success -> {
                            Log.d(TAG, "movieList = ${viewModel.movieList?.items}")
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