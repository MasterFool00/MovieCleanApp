package com.waffle.localmovieapplication.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.waffle.localmovieapplication.R
import com.waffle.localmovieapplication.databinding.FragmentSplashBinding
import com.waffle.localmovieapplication.ui.home.HomeViewModel
import com.waffle.core.base.BaseFragment
import org.koin.android.ext.android.inject

class SplashFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
    override fun onViewCreated(savedInstanceState: Bundle?) {

    }
    override fun observeData() {

    }

    override fun init() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }, 1000)
    }
}