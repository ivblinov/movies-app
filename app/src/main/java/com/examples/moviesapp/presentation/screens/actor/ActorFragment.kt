package com.examples.moviesapp.presentation.screens.actor

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.examples.moviesapp.R
import com.examples.moviesapp.databinding.FragmentActorBinding
import com.examples.moviesapp.presentation.recyclers.adapters.BestFilmAdapter
import com.examples.moviesapp.presentation.states.State
import com.examples.moviesapp.utils.appComponent
import kotlinx.coroutines.launch
import javax.inject.Inject

const val ACTOR = "ACTOR"

class ActorFragment : Fragment() {

    private var _binding: FragmentActorBinding? = null
    private val binding get() = _binding!!

    private var actorId: Int? = null
    private var professionKey = ACTOR

    @Inject
    lateinit var viewModel: ActorViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            actorId = it.getInt(ACTOR_ID_KEY)
            professionKey = it.getString(PROFESSION_KEY, ACTOR)
            actorId?.let { viewModel.getActor(it, professionKey) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe()

        binding.best.showAdditionalText()
        binding.best.setAdapter(BestFilmAdapter(clickFilm = ::clickBestFilm))

        binding.photo.setOnClickListener {
            showPhotoDialog()
        }
    }

    private fun clickBestFilm(filmId: Int) {
        viewModel.navigateToBestFilm(filmId)
    }

    private fun showPhotoDialog() {
        val dialog =
            Dialog(requireContext(), android.R.style.Theme_Material_Light_NoActionBar_Fullscreen)
        dialog.setContentView(R.layout.dialog_full_photo)

        val photo = dialog.findViewById<ImageView>(R.id.full_screen_image)
        Glide
            .with(requireContext())
            .load(viewModel.actor?.posterUrl)
            .into(photo)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog.show()

        photo.setOnClickListener {
            dialog.dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun inject() {
        requireContext().appComponent().inject(this)
    }

    private fun subscribe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch {
                    viewModel.state.collect { state ->
                        when (state) {
                            State.Loading -> {}
                            State.Success -> {
                                binding.nameActor.text =
                                    viewModel.actor?.nameRu ?: viewModel.actor?.nameEn
                                binding.profession.text = viewModel.actor?.profession
                                Glide
                                    .with(requireContext())
                                    .load(viewModel.actor?.posterUrl)
                                    .apply(RequestOptions().transform(RoundedCorners(dpToPx(4))))
                                    .into(binding.photo)
                            }
                        }
                    }
                }
                launch {
                    viewModel.bestState.collect { state ->
                        when (state) {
                            State.Loading -> {}
                            State.Success -> {
                                binding.best.getBestFilmAdapter()
                                    .updateBestFilms(viewModel.bestFilms)
                            }
                        }
                    }
                }
            }
        }
    }

    fun dpToPx(dp: Int): Int {
        val density = Resources.getSystem().displayMetrics.density
        return (dp * density).toInt()
    }

    companion object {

        fun createBundle(actorId: Int, professionKey: String): Bundle {
            val bundle = bundleOf()
            bundle.putInt(ACTOR_ID_KEY, actorId)
            bundle.putString(PROFESSION_KEY, professionKey)
            return bundle
        }

        private const val ACTOR_ID_KEY = "ACTOR_ID_KEY"
        private const val PROFESSION_KEY = "PROFESSION_KEY"
    }
}