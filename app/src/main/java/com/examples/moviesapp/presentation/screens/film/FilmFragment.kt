package com.examples.moviesapp.presentation.screens.film

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.examples.moviesapp.databinding.FragmentFilmBinding
import com.examples.moviesapp.domain.models.film.FilmInfoModel
import com.examples.moviesapp.presentation.recyclers.adapters.StaffAdapter
import com.examples.moviesapp.presentation.states.State
import com.examples.moviesapp.utils.appComponent
import kotlinx.coroutines.launch
import javax.inject.Inject

const val GRID_SIZE = 20
const val STAFF_GRID_SIZE = 6

class FilmFragment : Fragment() {

    private var _binding: FragmentFilmBinding? = null
    private val binding get() = _binding!!
    private var filmId: Int? = null
    private var isExpanded = false
    private val maxSymbols = 250

    @Inject
    lateinit var viewModel: FilmViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            filmId = it.getInt(FILM_KEY)
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
        binding.staffRV.adapter = StaffAdapter(clickPerson = ::navigateToActor)

        subscribe()

        filmId?.let {
            viewModel.getFilmInfo(it)
            viewModel.getCastList(it)
        }
    }
    
    private fun navigateToActor(actorId: Int, professionKey: String) {
        viewModel.navigateToActor(actorId, professionKey)
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
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch {
                    viewModel.state.collect { state ->
                        when (state) {
                            State.Loading -> {}
                            State.Success -> {
                                val numberActors = viewModel.actors.size
                                binding.numberActors.text =
                                    if (numberActors > GRID_SIZE) numberActors.toString() else ""
                                getActorAdapter().updateList(viewModel.actors)
                            }
                        }
                    }
                }
                launch {
                    viewModel.staffState.collect { state ->
                        when (state) {
                            State.Loading -> {}
                            State.Success -> {
                                val numberStaff = viewModel.staffList.size
                                binding.numberPersons.text = when {
                                    numberStaff > STAFF_GRID_SIZE -> numberStaff.toString()
                                    numberStaff == 1 -> {
                                        setSpanCount()
                                        ""
                                    }

                                    else -> ""
                                }
                                getStaffAdapter().updateList(viewModel.staffList)
                            }
                        }
                    }
                }
                launch {
                    viewModel.infoState.collect { state ->
                        when (state) {
                            State.Loading -> {}
                            State.Success -> {
                                viewModel.filmInfo?.let { filmInfo ->
                                    setFilmInfo(filmInfo)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setFilmInfo(filmInfo: FilmInfoModel) {
        Glide
            .with(binding.poster.context)
            .load(filmInfo.posterUrl)
            .into(binding.poster)

        val ratingName = "${filmInfo.ratingKinopoisk ?: ""} ${filmInfo.nameRu ?: filmInfo.nameEn}"
        binding.rating.text = ratingName
        binding.year.text = getYearGenre(filmInfo)
        binding.country.text = getCountryDuration(filmInfo)
        filmInfo.shortDescription?.let { binding.shortDescription.text = it }
        filmInfo.description?.let { setDescription(it) }
    }

    fun setDescription(descriptionText: String) {
        with(binding.description) {
            if (descriptionText.length > maxSymbols) {
                val shortText = descriptionText.take(maxSymbols) + "..."
                binding.description.text = shortText
                binding.description.setOnClickListener {
                    isExpanded = !isExpanded
                    binding.description.text = if (isExpanded) descriptionText else shortText
                }
            } else {
                binding.description.text = descriptionText
                binding.description.setOnClickListener(null)
            }
        }
    }

    private fun getCountryDuration(filmInfo: FilmInfoModel): String {
        val country = filmInfo.countries?.firstOrNull()?.country
        val duration = filmInfo.filmLength?.let { length ->
            val hours = length / 60
            val minutes = length % 60
            buildString {
                if (hours > 0) append("$hours ч ")
                if (minutes > 0) append("$minutes мин")
            }.trim()
        }
        val age = filmInfo.ratingAgeLimits?.let { ageLimit ->
            ageLimit.removePrefix("age") + "+"
        }

        return listOfNotNull(country, duration, age)
            .joinToString(", ")
    }

    private fun getYearGenre(filmInfo: FilmInfoModel): String {
        val year = filmInfo.year?.toString()
        val genres = filmInfo.genres?.take(2)?.map { it.genre }?.joinToString(", ")

        return listOfNotNull(year, genres)
            .filter { it.isNotBlank() }
            .joinToString(", ")
    }

    private fun setSpanCount() {
        val layoutManager = getStaffLayoutManager()
        layoutManager.spanCount = 1
        binding.staffRV.layoutManager = layoutManager
    }

    private fun getActorAdapter() = binding.actorRV.adapter as StaffAdapter
    private fun getStaffAdapter() = binding.staffRV.adapter as StaffAdapter
    private fun getStaffLayoutManager() = binding.staffRV.layoutManager as GridLayoutManager

    companion object {
        fun createBundle(filmId: Int) = bundleOf(FILM_KEY to filmId)

        private const val FILM_KEY = "FILM_KEY"
    }
}