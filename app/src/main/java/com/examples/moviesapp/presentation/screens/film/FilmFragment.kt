package com.examples.moviesapp.presentation.screens.film

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.examples.moviesapp.databinding.FragmentFilmBinding
import com.examples.moviesapp.domain.models.MovieModel
import com.examples.moviesapp.presentation.recyclers.adapters.StaffAdapter
import com.examples.moviesapp.presentation.states.State
import com.examples.moviesapp.utils.appComponent
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MyLog"
const val GRID_SIZE = 20

class FilmFragment : Fragment() {

    private var _binding: FragmentFilmBinding? = null
    private val binding get() = _binding!!

    private var movie: MovieModel? = null

    @Inject
    lateinit var viewModel: FilmViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movie = it.getParcelable(MOVIE_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.actorRV.adapter = StaffAdapter(clickPerson = ::navigateToActor)

        subscribe()

        movie?.kinopoiskId?.let { viewModel.getCastList(it) }

        movie?.let { film ->
            Glide
                .with(requireContext())
                .load(film.posterUrl)
                .into(binding.poster)
        }

        binding.year.text = movie?.year.toString()
        binding.country.text = movie?.countries[0]?.country
    }

    private fun navigateToActor(actorId: Int) {
        viewModel.navigateToActor(actorId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun inject() {
        requireContext().appComponent().inject(this)
    }

    private fun subscribe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    State.Loading -> {}
                    State.Success -> {
                        val numberActors = viewModel.actors.size
                        binding.numberActors.text =
                            if (numberActors > GRID_SIZE) numberActors.toString() else ""
                        getStaffAdapter().updateList(viewModel.actors)
                    }
                }
            }
        }
    }

    private fun getStaffAdapter() = binding.actorRV.adapter as StaffAdapter

    companion object {
        fun createBundle(movie: MovieModel) = bundleOf(MOVIE_KEY to movie)

        private const val MOVIE_KEY = "MOVIE_KEY"
    }
}