package com.examples.moviesapp.presentation.recyclers.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.examples.moviesapp.databinding.MovieItemBinding
import com.examples.moviesapp.domain.models.MovieModel

private const val ITEM_COUNT_IN_LIST = 20

class MovieAdapter(
    private val onClick: (Int) -> Unit,
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val data: MutableList<MovieModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick = onClick,
        )
    }

    override fun getItemCount(): Int = ITEM_COUNT_IN_LIST

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = data.getOrNull(position)
        with(holder.binding) {
            title.text = item?.nameRu ?: ""
            genre.text = item?.genres?.firstOrNull()?.genre
            item?.let {
                Glide
                    .with(poster.context)
                    .load(it.posterUrlPreview)
                    .into(poster)
            }
        }
        item?.let { holder.onBind(it) }
    }

    inner class MovieViewHolder(
        val binding: MovieItemBinding,
        private val onClick: (Int) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(movie: MovieModel) {
            binding.root.setOnClickListener {
                onClick(movie.kinopoiskId)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<MovieModel>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}