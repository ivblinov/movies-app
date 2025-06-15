package com.examples.moviesapp.domain

import com.examples.moviesapp.data.Repository
import com.examples.moviesapp.domain.models.MovieListModel
import com.examples.moviesapp.entities.MovieList
import com.examples.moviesapp.utils.getDaysList
import com.examples.moviesapp.utils.getSetPairMonthYear
import com.examples.moviesapp.utils.shuffleList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

class LoadPremiereListUseCase @Inject constructor(
    private val repository: Repository
) {
    private val amountOfDays: Byte = 14
    private var daysList = mutableListOf<LocalDate>()
    private var daysListToString = listOf<String>()
    private var setPairMonthYear = mutableListOf<Pair<Int, String>>()

    init {
        daysList = getDaysList(amountOfDays)
        daysListToString = daysList.map { it.toString() }
        setPairMonthYear = getSetPairMonthYear(daysList)
    }

    suspend fun getPremiereList(): MovieListModel? {
        val movieList = launchLoadingPremiere(setPairMonthYear)
        val movie = filterMovieList(movieList)
        return shuffleList(movie)
    }

    private suspend fun launchLoadingPremiere(
        setPairMonthYear: MutableList<Pair<Int, String>>
    ): MovieListModel? {
        var movieList: MovieListModel? = null
        CoroutineScope(Dispatchers.IO).launch {
            setPairMonthYear.forEach { (year, month) ->
                launch {
                    val movieListLocal = loadPremiereList(year, month)
                    if (movieList == null) {
                        movieList = movieListLocal
                    } else {
                        movieList!!.total += movieListLocal.total
                        movieList!!.items += movieListLocal.items
                    }
                }
            }
        }.join()
        return movieList
    }

    private fun filterMovieList(movieList: MovieListModel?): MovieListModel? {
        return if (movieList != null) {
            val movie = movieList.items.filter {
                it.premiereRu in daysListToString && it.nameRu != ""
            }
            movieList.total = movie.size
            movieList.items = movie
            movieList
        } else {
            null
        }
    }

    private suspend fun loadPremiereList(year: Int, month: String): MovieListModel {
        return repository.loadPremiereList(year, month)
    }
}