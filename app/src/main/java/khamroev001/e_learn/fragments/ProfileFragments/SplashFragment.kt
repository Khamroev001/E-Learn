package khamroev001.e_learn.fragments.ProfileFragments

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
import com.google.gson.Gson
import khamroev001.e_learn.R
import khamroev001.e_learn.databinding.FragmentSplashBinding
import khamroev001.e_learn.utils.API


class SplashFragment : Fragment() {

       lateinit var sh: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding=FragmentSplashBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        sh= activity?.getSharedPreferences("data", MODE_PRIVATE)!!
        var gson = Gson()
        var edit = sh.edit()

 var api=API.newInstance(requireContext())




        Handler(Looper.getMainLooper()).postDelayed({
            if (api.getRegUser()!=null){
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            }else{
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }
        },4700 )
        return binding.root
    }

}