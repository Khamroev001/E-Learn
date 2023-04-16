package khamroev001.e_learn.fragments.MainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import khamroev001.e_learn.R
import khamroev001.e_learn.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

  lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment










        return binding.root
    }

}