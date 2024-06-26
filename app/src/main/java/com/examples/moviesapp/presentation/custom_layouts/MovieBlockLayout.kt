package com.examples.moviesapp.presentation.custom_layouts

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.examples.moviesapp.R
import com.examples.moviesapp.databinding.MovieBlockLayoutBinding

class MovieBlockLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding: MovieBlockLayoutBinding

    init {
        orientation = VERTICAL
        val view = inflate(context, R.layout.movie_block_layout, this)
        binding = MovieBlockLayoutBinding.bind(view)
    }

    fun setMainText(text: String) {
        binding.movieBlockTitle.setMainText(text)
    }
}