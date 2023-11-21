package com.waffle.localmovieapplication.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.waffle.core.local.model.Popular
import com.waffle.core.utils.loadImage
import com.waffle.localmovieapplication.R
import com.waffle.localmovieapplication.databinding.ItemPopularBinding

class HomeAdapter(
    private val list : List<Popular>,
    private val fragment: Fragment
): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    class ViewHolder(val binding : ItemPopularBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_popular, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        val mProject = list[position]
        holder.itemView.setOnClickListener {
            findNavController(fragment).navigate(
                R.id.action_homeFragment_to_homeDetailFragment, bundleOf(
                    HomeDetailFragment.DATA to mProject)
            )
        }
        bindDataToView(binding, mProject)
    }

    override fun getItemCount() = list.size

    private fun bindDataToView(binding: ItemPopularBinding, item : Popular){
        binding.apply {
            tvMovieTitle.text = item.name
            tvMovieRating.text = item.star.toString()
            tvMovieYear.text = item.date
            tvMoviePopularity.text = item.popularity.toString()
            ivThumbnail.loadImage(item.posterPath)

        }
    }

}