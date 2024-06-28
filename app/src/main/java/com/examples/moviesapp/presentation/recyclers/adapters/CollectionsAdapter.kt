package com.examples.moviesapp.presentation.recyclers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.examples.moviesapp.databinding.MovieItemBinding
import com.examples.moviesapp.entities.CollectionItem
import javax.inject.Inject

class CollectionsAdapter @Inject constructor() :
    RecyclerView.Adapter<CollectionsAdapter.CollectionsViewHolder>() {

    private val data: MutableList<CollectionItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionsViewHolder {
        return CollectionsViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = 20

    override fun onBindViewHolder(holder: CollectionsViewHolder, position: Int) {
        val item = data.getOrNull(position)
        with(holder.binding) {
            title.text = item?.nameRu ?: ""
            genre.text = item?.genres?.firstOrNull()?.genre
            item?.ratingKinopoisk?.let {
                rating.visibility = View.VISIBLE
                rating.setText(it.toString())
            }
            item?.let {
                Glide
                    .with(poster.context)
                    .load(it.posterUrlPreview)
                    .into(poster)
            }
        }
    }

    inner class CollectionsViewHolder(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun setData(data: List<CollectionItem>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}