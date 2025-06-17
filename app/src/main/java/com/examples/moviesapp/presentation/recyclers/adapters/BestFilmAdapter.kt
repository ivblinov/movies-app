package com.examples.moviesapp.presentation.recyclers.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.examples.moviesapp.databinding.MovieItemBinding
import com.examples.moviesapp.domain.models.BestFilmModel

class BestFilmAdapter(
    private var bestFilms: List<BestFilmModel> = listOf(),
    private val clickFilm: (Int) -> Unit,
) : RecyclerView.Adapter<BestFilmAdapter.ViewHolder>() {

    class ViewHolder(
        val binding: MovieItemBinding,
        private val clickFilm: (Int) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(film: BestFilmModel) {
            binding.title.text = film.nameRu ?: film.nameEn
            binding.genre.text = film.genres.firstOrNull()?.genre ?: ""
            film.ratingKinopoisk?.let { rating ->
                binding.rating.setText(rating.toString())
                binding.rating.visibility = View.VISIBLE
            }
            Glide
                .with(binding.poster.context)
                .load(film.posterUrl)
                .into(binding.poster)

            binding.root.setOnClickListener {
                film.kinopoiskId?.let { clickFilm.invoke(it) }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(
        binding = getBinding(parent),
        clickFilm = clickFilm
    )

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.onBind(bestFilms[position])
    }

    override fun getItemCount() =
        if (bestFilms.size > MAX_ITEM_COUNT) MAX_ITEM_COUNT else bestFilms.size

    private fun getBinding(parent: ViewGroup): MovieItemBinding =
        MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    @SuppressLint("NotifyDataSetChanged")
    fun updateBestFilms(list: List<BestFilmModel>) {
        this.bestFilms = list
        notifyDataSetChanged()
    }

    companion object {

        private const val MAX_ITEM_COUNT = 8
    }
}