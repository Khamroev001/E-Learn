package khamroev001.e_learn.fragments

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.Hold
import com.google.gson.Gson
import khamroev001.e_learn.R
import khamroev001.e_learn.databinding.FragmentSplashBinding
import khamroev001.e_learn.model.User


class SplashFragment : Fragment() {

       lateinit var sh: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding=FragmentSplashBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        sh= activity?.getSharedPreferences("user", MODE_PRIVATE)!!
        var gson = Gson()
        var edit = sh.edit()





        Handler(Looper.getMainLooper()).postDelayed({
            if (sh.getString("user","")==""){
                findNavController().navigate(R.id.action_splashFragment_to_firstImgFragment)
            }else{
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }
        },4700 )
        return binding.root
    }

}