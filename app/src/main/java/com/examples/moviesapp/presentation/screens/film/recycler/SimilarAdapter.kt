package com.examples.moviesapp.presentation.screens.film.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.examples.moviesapp.databinding.MovieItemBinding
import com.examples.moviesapp.domain.models.film.SimilarModel
import com.examples.moviesapp.presentation.screens.film.GRID_SIZE

class SimilarAdapter(
    private var similarList: List<SimilarModel> = listOf(),
    private val onClick: (Int) -> Unit,
) : RecyclerView.Adapter<SimilarAdapter.ViewHolder>() {

    class ViewHolder(
        val binding: MovieItemBinding,
        private val onClick: (Int) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(film: SimilarModel) {
            binding.root.setOnClickListener { film.filmId?.let { onClick.invoke(it) } }

            Glide
                .with(binding.poster)
                .load(film.posterUrl)
                .into(binding.poster)
            binding.title.text = film.nameRu ?: film.nameEn ?: ""
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
        val film = similarList[position]
        holder.onBind(film)
    }

    override fun getItemCount() = if (similarList.size > GRID_SIZE) GRID_SIZE else similarList.size

    private fun getBinding(parent: ViewGroup): MovieItemBinding =
        MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<SimilarModel>) {
        this.similarList = list
        notifyDataSetChanged()
    }
}