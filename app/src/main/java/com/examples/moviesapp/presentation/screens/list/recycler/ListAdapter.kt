package com.examples.moviesapp.presentation.screens.list.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.examples.moviesapp.databinding.MovieItemBinding
import com.examples.moviesapp.domain.models.MovieModel

class ListAdapter(
    private var movieList: List<MovieModel> = listOf(),
    private val onClick: (Int) -> Unit,
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(
        val binding: MovieItemBinding,
        private val onClick: (Int) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(movie: MovieModel) {
            binding.title.text = movie.nameRu
            binding.genre.text = movie.genres.firstOrNull()?.genre ?: ""
            Glide
                .with(binding.poster.context)
                .load(movie.posterUrlPreview)
                .into(binding.poster)
            binding.root.setOnClickListener {
                onClick(movie.kinopoiskId)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(
        binding = getBinding(parent),
        onClick = onClick,
    )

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val movie = movieList[position]
        holder.onBind(movie)
    }

    override fun getItemCount() = movieList.size

    private fun getBinding(parent: ViewGroup) =
        MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<MovieModel>) {
        this.movieList = list
        notifyDataSetChanged()
    }
}