package com.examples.moviesapp.presentation.recyclers.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.examples.moviesapp.databinding.MovieItemBinding
import com.examples.moviesapp.entities.Movie

private const val TAG = "MyLog"

class MovieAdapter(
    private val onClick: () -> Unit,
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val data: MutableList<Movie> = ArrayList()

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

    override fun getItemCount(): Int = 20

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
        holder.onBind()
    }

    inner class MovieViewHolder(
        val binding: MovieItemBinding,
        private val onClick: () -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            binding.root.setOnClickListener {
                onClick()
            }
        }
    }

    fun setData(data: List<Movie>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}