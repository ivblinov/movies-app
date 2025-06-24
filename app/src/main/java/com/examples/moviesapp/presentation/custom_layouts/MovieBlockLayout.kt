package com.examples.moviesapp.presentation.custom_layouts

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import com.examples.moviesapp.R
import com.examples.moviesapp.databinding.MovieBlockLayoutBinding
import com.examples.moviesapp.presentation.recyclers.adapters.BestFilmAdapter
import com.examples.moviesapp.presentation.recyclers.adapters.CollectionsAdapter
import com.examples.moviesapp.presentation.recyclers.adapters.DynamicAdapter
import com.examples.moviesapp.presentation.recyclers.adapters.MovieAdapter
import com.examples.moviesapp.presentation.screens.film.recycler.SimilarAdapter

class MovieBlockLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding: MovieBlockLayoutBinding
    var additionalText: AppCompatTextView? = null
    var mainText: AppCompatTextView? = null

    init {
        orientation = VERTICAL
        val view = inflate(context, R.layout.movie_block_layout, this)
        binding = MovieBlockLayoutBinding.bind(view)

        mainText = binding.movieBlockTitle.mainText
        additionalText = binding.movieBlockTitle.additionalText

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MovieBlockLayout)
        val mainText = typedArray.getString(R.styleable.MovieBlockLayout_block_main_text)
        val additionalText = typedArray.getString(R.styleable.MovieBlockLayout_block_additional_text)

        binding.movieBlockTitle.mainText?.text = mainText
        binding.movieBlockTitle.additionalText?.text = additionalText
        binding.movieBlockTitle.additionalText?.setOnClickListener {
            binding.movieBlockTitle.additionalTextClicked?.invoke()
        }
        typedArray.recycle()
    }

    fun showAdditionalText() {
        binding.movieBlockTitle.showAdditionalText()
    }

    fun setAdapter(adapter: MovieAdapter) {
        binding.recycler.adapter = adapter
    }

    fun setAdapter(adapter: CollectionsAdapter) {
        binding.recycler.adapter = adapter
    }

    fun setAdapter(adapter: DynamicAdapter) {
        binding.recycler.adapter = adapter
    }

    fun setAdapter(adapter: BestFilmAdapter) {
        binding.recycler.adapter = adapter
    }

    fun setAdapter(adapter: SimilarAdapter) {
        binding.recycler.adapter = adapter
    }

    fun getMovieAdapter(): MovieAdapter = binding.recycler.adapter as MovieAdapter

    fun getCollectionsAdapter(): CollectionsAdapter = binding.recycler.adapter as CollectionsAdapter

    fun getDynamicAdapter(): DynamicAdapter = binding.recycler.adapter as DynamicAdapter

    fun getBestFilmAdapter(): BestFilmAdapter = binding.recycler.adapter as BestFilmAdapter

    fun getSimilarAdapter(): SimilarAdapter = binding.recycler.adapter as SimilarAdapter
}