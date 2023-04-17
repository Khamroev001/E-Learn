package khamroev001.e_learn.fragments.MainFragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import khamroev001.e_learn.adapter.OfferAdapter
import khamroev001.e_learn.databinding.FragmentHomeBinding
import khamroev001.e_learn.model.Discount_offers


class HomeFragment : Fragment() {

  lateinit var binding: FragmentHomeBinding
   lateinit var offerlist:MutableList<Discount_offers>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment

    offerlist= mutableListOf(Discount_offers(40,"Today's special","Get discount for every course order",Color.BLUE),Discount_offers(30,"Today's special","Get discount for every course order",Color.RED),Discount_offers(40,"Today's special","Get discount for every course order",Color.LTGRAY),Discount_offers(70,"Today's special","Get discount for every course order",Color.MAGENTA),Discount_offers(20,"Today's special","Get discount for every course order",Color.YELLOW))


        val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
       var adapter=OfferAdapter(requireContext(),offerlist)
        binding.rvOffer.adapter=adapter

        binding.rvOffer.layoutManager=layoutManager





        return binding.root
    }

}