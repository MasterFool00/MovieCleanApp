package com.waffle.favoritefeature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.waffle.core.local.entity.PopularEntity
import com.waffle.core.local.model.Popular
import com.waffle.core.utils.loadImage
import com.waffle.favoritefeature.databinding.ItemFavoriteBinding

class FavoriteAdapter(
    private val list : List<Popular>,
    private val fragment: Fragment
): RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    class ViewHolder(val binding : ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_favorite, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        val mProject = list[position]
        holder.itemView.setOnClickListener {
            fragment.findNavController().navigate(
                R.id.action_favoriteFragment_to_favoriteDetailFragment, bundleOf(
                    FavoriteDetailFragment.DATA to mProject)
            )
        }
        bindDataToView(binding, mProject)
    }

    override fun getItemCount() = list.size

    private fun bindDataToView(binding: ItemFavoriteBinding, item : Popular){
        binding.apply {
            tvMovieTitle.text = item.name
            tvMovieRating.text = item.star.toString()
            tvMovieYear.text = item.date
            tvMoviePopularity.text = item.popularity.toString()
            ivThumbnail.loadImage(item.posterPath)

        }
    }

}