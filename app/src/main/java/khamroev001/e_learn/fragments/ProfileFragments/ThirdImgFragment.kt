package khamroev001.e_learn.fragments.ProfileFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import khamroev001.e_learn.R
import khamroev001.e_learn.databinding.FragmentThirdImgBinding

class ThirdImgFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding=FragmentThirdImgBinding.inflate(inflater,container,false)

       binding.button.setOnClickListener{
           findNavController().navigate(R.id.action_thirdImgFragment_to_createAccFragment)
       }

        return binding.root
    }
}