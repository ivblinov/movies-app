package com.examples.moviesapp.presentation.screens.actor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examples.moviesapp.domain.models.actor.BestFilmModel
import com.examples.moviesapp.domain.models.actor.FilmOfActorModel
import com.examples.moviesapp.domain.models.actor.PersonModel
import com.examples.moviesapp.domain.use_cases.best_film.BestFilmUseCase
import com.examples.moviesapp.domain.use_cases.staff.PersonUseCase
import com.examples.moviesapp.presentation.navigation.Navigator
import com.examples.moviesapp.presentation.states.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ActorViewModel @Inject constructor(
    private val useCase: PersonUseCase,
    private val bestFilmUseCase: BestFilmUseCase,
    private val navigator: Navigator,
) : ViewModel() {

    var actor: PersonModel? = null
    val bestFilms = mutableListOf<BestFilmModel>()

    private val _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()

    private val _bestState = MutableStateFlow<State>(State.Success)
    val bestState = _bestState.asStateFlow()

    fun getActor(id: Int, professionKey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = State.Loading
            actor = useCase.getPerson(id)
            _state.value = State.Success

            val best10Films = getBestFilms(actor, professionKey)
            loadBestFilms(best10Films)
        }
    }

    fun loadBestFilms(best10Films: List<FilmOfActorModel>?) {
        _bestState.value = State.Loading
        best10Films?.forEach { film ->
            film.filmId?.let {
                viewModelScope.launch(Dispatchers.IO) {
                    val bestFilm = bestFilmUseCase.getBestFilm(it)
                    bestFilms.add(bestFilm)
                    if (bestFilms.size == best10Films.size)
                        _bestState.value = State.Success
                }
            }
        }
    }

    fun navigateToBestFilm(filmId: Int) {
        navigator.navigateToBestFilm(filmId)
    }

    private fun getBestFilms(actor: PersonModel?, professionKey: String): List<FilmOfActorModel>? {
        actor?.films?.let { films ->
            val uniqueFilms = films.toSet().toList()
            val bestFilms = uniqueFilms
                .filter { it.professionKey == professionKey }
                .sortedBy { it.rating }.reversed()
            return if (bestFilms.size > 10) bestFilms.subList(0, 10) else bestFilms
        }
        return null
    }
}