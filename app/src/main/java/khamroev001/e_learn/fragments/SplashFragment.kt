package khamroev001.e_learn.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.Hold
import khamroev001.e_learn.R
import khamroev001.e_learn.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding=FragmentSplashBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment

        Handler(Looper.getMainLooper()).postDelayed({
                                                    findNavController().navigate(R.id.action_splashFragment_to_firstImgFragment)
        },4700)
        return binding.root
    }

}