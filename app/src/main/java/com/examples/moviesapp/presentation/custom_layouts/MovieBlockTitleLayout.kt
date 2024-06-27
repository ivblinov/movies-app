package com.examples.moviesapp.presentation.custom_layouts

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.examples.moviesapp.R
import com.examples.moviesapp.databinding.MovieBlockTitleLayoutBinding

class MovieBlockTitleLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding: MovieBlockTitleLayoutBinding
    var additionalTextClicked: (() -> Unit)? = null
    var mainText: AppCompatTextView? = null
    var additionalText: AppCompatTextView? = null

    init {
        val view = inflate(context, R.layout.movie_block_title_layout, this)
        binding = MovieBlockTitleLayoutBinding.bind(view)

        mainText = binding.mainText
        additionalText = binding.additionalText

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
}