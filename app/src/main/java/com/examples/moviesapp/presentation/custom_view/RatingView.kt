package com.examples.moviesapp.presentation.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.examples.moviesapp.databinding.RatingViewBinding

class RatingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding = RatingViewBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    private fun setText(text: String) {
        binding.textRating.text = text
    }
}