package com.waffle.localmovieapplication.ui.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.navigation.fragment.findNavController
import com.waffle.localmovieapplication.databinding.FragmentHomeDetailBinding
import com.waffle.core.local.entity.PopularEntity
import com.waffle.core.base.BaseFragment
import com.waffle.core.local.model.Popular
import com.waffle.core.utils.loadImage
import org.koin.android.ext.android.inject

class HomeDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeDetailBinding

    private val data by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(DATA, Popular::class.java) ?: Popular(0,null,null,null,null,null,null, null, false)
        } else {
            arguments?.getParcelable(DATA) ?: Popular(0,null,null,null,null,null,null, null, false)
        }
    }

    private val viewModel : HomeViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
    }

    override fun observeData() {
    }

    override fun init() {
        binding.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            tvMovieTitle.text = data.name
            tvMovieYearAndDuration.text = data.date
            tvMovieGenre.text = data.name
            tvMovieRating.text = data.star.toString()
            tvAboutMovie.text = data.overview
            ivBackdrop.loadImage(data.backdropPath)
            ivThumbnail.loadImage(data.posterPath)
            ivFavBorder.isGone = data.isFavorite
            ivFav.isGone = !data.isFavorite
            ivFavBorder.setOnClickListener{
                ivFavBorder.isGone = true
                ivFav.isGone = false
                data.isFavorite = true
                viewModel.updatePopular(data)
            }
            ivFav.setOnClickListener{
                ivFavBorder.isGone = false
                ivFav.isGone = true
                data.isFavorite = false
                viewModel.updatePopular(data)
            }
        }
    }
    companion object {
        const val DATA = "DiscoverDetailFragment.data"
    }
}