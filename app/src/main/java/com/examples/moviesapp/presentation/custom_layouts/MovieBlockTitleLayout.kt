package com.examples.moviesapp.presentation.custom_layouts

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.examples.moviesapp.R
import com.examples.moviesapp.databinding.MovieBlockTitleLayoutBinding

class MovieBlockTitleLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding: MovieBlockTitleLayoutBinding
    var additionalTextClicked: (() -> Unit)? = null

    init {
        val inflatedView = inflate(context, R.layout.movie_block_title_layout, this)
        binding = MovieBlockTitleLayoutBinding.bind(inflatedView)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MovieBlockTitleLayout)
        val mainText = typedArray.getString(R.styleable.MovieBlockTitleLayout_main_text)
        val additionalText = typedArray.getString(R.styleable.MovieBlockTitleLayout_additional_text)

        binding.mainText.text = mainText
        binding.additionalText.text = additionalText
        binding.additionalText.setOnClickListener {
            additionalTextClicked?.invoke()
        }
        typedArray.recycle()
    }

    fun setMainText(text: String) {
        binding.mainText.text = text
    }

    fun setAdditionalText(text: String) {
        binding.additionalText.text = text
    }

    fun changeAdditionalTextVisible() {
        binding.additionalText.visibility = View.VISIBLE
    }

    fun changeAdditionalTextGone() {
        binding.additionalText.visibility = View.GONE
    }

    fun changeArrowRightOnVisible() {
        binding.arrowRight.visibility = View.VISIBLE
    }

    fun changeArrowRightOnGone() {
        binding.arrowRight.visibility = View.GONE
    }
}