package com.examples.moviesapp.presentation.screens.film.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.examples.moviesapp.databinding.ItemImageBinding
import com.examples.moviesapp.domain.models.film.ImageItemModel

class ImageAdapter(
    private var imageList: List<ImageItemModel> = listOf()
) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(image: ImageItemModel) {
            Glide
                .with(binding.image)
                .load(image.imageUrl)
                .into(binding.image)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(
        binding = getBinding(parent)
    )

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val image = imageList[position]
        holder.onBind(image)
    }

    override fun getItemCount() = imageList.size

    private fun getBinding(parent: ViewGroup): ItemImageBinding =
        ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<ImageItemModel>) {
        this.imageList = list
        notifyDataSetChanged()
    }
}