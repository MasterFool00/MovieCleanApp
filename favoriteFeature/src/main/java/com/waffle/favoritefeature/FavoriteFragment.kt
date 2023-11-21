package com.waffle.favoritefeature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.waffle.core.base.BaseFragment
import com.waffle.core.base.Resource
import com.waffle.favoritefeature.databinding.FragmentFavoriteBinding
import org.koin.android.ext.android.inject
import xyz.hasnat.sweettoast.SweetToast

class FavoriteFragment : BaseFragment() {

    private lateinit var binding : FragmentFavoriteBinding

    private val viewModel : FavoriteViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
    }

    override fun observeData() {
        viewModel.apply {
            getPopularFavorite().observe(this@FavoriteFragment) { data ->
                when(data) {
                    is Resource.Error -> {
                        SweetToast.error(requireContext(), data.message)
                        hideLoading(binding.pbLoading)
                    }
                    is Resource.Loading -> {
                        showLoading(binding.pbLoading)
                    }
                    is Resource.Success -> {
                        binding.rvFavorite.apply {
                            adapter = FavoriteAdapter(data.data ?: listOf(), this@FavoriteFragment)
                            layoutManager = LinearLayoutManager(requireContext())
                        }
                        hideLoading(binding.pbLoading)
                    }
                }
            }
        }
    }

    override fun init() {
        binding.apply {
            btnBack.setOnClickListener { activity?.finish() }
        }
    }
}