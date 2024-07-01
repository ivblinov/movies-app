package com.examples.moviesapp

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.examples.moviesapp.domain.models.CollectionsModel
import com.examples.moviesapp.domain.models.MovieListModel
import java.time.LocalDate

fun setVisible(additionalText: AppCompatTextView?) {
    additionalText?.visibility = View.VISIBLE
}

fun getSetPairMonthYear(daysList: MutableList<LocalDate>): MutableList<Pair<Int, String>> {
    val setMonth = mutableListOf<Pair<Int, String>>()
    daysList.forEach { date ->
        val month = date.month.toString()
        val year = date.year
        val item = Pair(year, month)
        if (item !in setMonth) {
            setMonth.add(item)
        }
    }
    return setMonth
}

fun getDaysList(amountOfDays: Byte): MutableList<LocalDate> {
    val daysList = mutableListOf<LocalDate>()
    var date = LocalDate.now()
    repeat(amountOfDays.toInt()) {
        date = date.plusDays(1L)
        daysList.add(date)
    }
    return daysList
}

fun shuffleList(list: MovieListModel?): MovieListModel? {
    val newMovieListModel = list?.let {
        MovieListModel(
        total = it.total,
        items = it.items.shuffled()
        )
    }
    return newMovieListModel
}

fun shuffleList(list: CollectionsModel?): CollectionsModel? {
    val newCollectionsModel = list?.let {
        CollectionsModel(
            total = it.total,
            items = it.items.shuffled(),
            totalPages = it.totalPages
        )
    }
    return newCollectionsModel
}