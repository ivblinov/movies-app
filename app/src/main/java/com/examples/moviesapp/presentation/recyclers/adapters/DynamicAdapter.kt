package com.examples.moviesapp.presentation.recyclers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.examples.moviesapp.databinding.MovieItemBinding
import com.examples.moviesapp.entities.Film
import javax.inject.Inject

class DynamicAdapter @Inject constructor() :
    RecyclerView.Adapter<DynamicAdapter.DynamicViewHolder>() {

    private val data: MutableList<Film> = ArrayList()

    inner class DynamicViewHolder(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DynamicViewHolder {
        return DynamicViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return if (data.size < 20)
            data.size
        else
            20
    }

    override fun onBindViewHolder(holder: DynamicViewHolder, position: Int) {
        val item = data.getOrNull(position)
        with(holder.binding) {
            title.text = item?.nameRu ?: item?.nameOriginal
            genre.text = item?.genres?.firstOrNull()?.genre
            item?.ratingKinopoisk?.let {
                rating.visibility = View.VISIBLE
                rating.setText(it.toString())
            }
            item?.let { film ->
                film.posterUrlPreview?.let { posterUrlPreview ->
                    Glide
                        .with(poster.context)
                        .load(posterUrlPreview)
                        .into(poster)
                } ?: let {
                    Glide
                        .with(poster.context)
                        .load(film.posterUrl)
                        .into(poster)
                }
            }
        }
    }

    fun setData(data: List<Film>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}