package com.examples.moviesapp.presentation.recyclers.adapters

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.examples.moviesapp.databinding.ItemPersonBinding
import com.examples.moviesapp.entities.film.Staff
import com.examples.moviesapp.presentation.screens.actor.ACTOR
import com.examples.moviesapp.presentation.screens.film.GRID_SIZE

class StaffAdapter(
    private var staffList: List<Staff> = listOf(),
    private val clickPerson: (Int, String) -> Unit,
) : RecyclerView.Adapter<StaffAdapter.ViewHolder>() {

    class ViewHolder(
        val binding: ItemPersonBinding,
        private val clickPerson: (Int, String) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(person: Staff) {

            Glide
                .with(binding.photoPerson.context)
                .load(person.posterUrl)
                .apply(RequestOptions().transform(RoundedCorners(dpToPx(4))))
                .into(binding.photoPerson)

            binding.actorName.text =
                if (person.nameRu.isNullOrEmpty()) person.nameEn else person.nameRu
            binding.characterName.text =
                capitalizeFirstLetter(person.description) ?: getProfession(person.professionKey)

            binding.root.setOnClickListener {
                person.staffId?.let { clickPerson.invoke(it, person.professionKey ?: ACTOR) }
            }
        }

        fun capitalizeFirstLetter(text: String?): String? {
            return text?.replaceFirstChar { it.titlecase() }
        }

        private fun getProfession(professionKey: String?): String = when (professionKey) {
            "DIRECTOR" -> "Режиссёр"
            "PRODUCER" -> "Продюсер"
            "WRITER" -> "Сценарист"
            "COMPOSER" -> "Композитор"
            "OPERATOR" -> "Оператор"
            "DESIGN" -> "Художник"
            "EDITOR" -> "Монтажёр"
            "VOICE_DIRECTOR" -> "Режиссёр дубляжа"
            else -> ""
        }

        fun dpToPx(dp: Int): Int {
            val density = Resources.getSystem().displayMetrics.density
            return (dp * density).toInt()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(
        binding = getBinding(parent),
        clickPerson = clickPerson,
    )

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val person = staffList[position]
        holder.onBind(person)
    }

    override fun getItemCount() = if (staffList.size > GRID_SIZE) GRID_SIZE else staffList.size

    private fun getBinding(parent: ViewGroup): ItemPersonBinding =
        ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<Staff>) {
        this.staffList = list
        notifyDataSetChanged()
    }
}