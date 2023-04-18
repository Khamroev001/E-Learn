package khamroev001.e_learn.fragments.MainFragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager.BadTokenException
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import khamroev001.e_learn.R
import khamroev001.e_learn.adapter.Btn_CoursesAdapter
import khamroev001.e_learn.adapter.Mentor
import khamroev001.e_learn.adapter.MentorAdapter
import khamroev001.e_learn.adapter.OfferAdapter
import khamroev001.e_learn.databinding.FragmentHomeBinding
import khamroev001.e_learn.model.Btn_Courses
import khamroev001.e_learn.model.Discount_offers


class HomeFragment : Fragment() {

  lateinit var binding: FragmentHomeBinding
   lateinit var offerlist:MutableList<Discount_offers>
   lateinit var mentorlist:MutableList<Mentor>
   lateinit var btn_courseslist:MutableList<Btn_Courses>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment

    offerlist= mutableListOf(Discount_offers(40,"Today's special","Get discount for every course order",Color.BLUE),Discount_offers(30,"Today's special","Get discount for every course order",Color.RED),Discount_offers(40,"Today's special","Get discount for every course order",Color.LTGRAY),Discount_offers(70,"Today's special","Get discount for every course order",Color.MAGENTA),Discount_offers(20,"Today's special","Get discount for every course order",Color.parseColor("#466DFA")))
    mentorlist= mutableListOf(Mentor("Jordan \n Peterson", R.drawable.mentor),Mentor("Jordan \n Peterson", R.drawable.mentor),Mentor("Jordan \n Peterson", R.drawable.mentor),Mentor("Jordan \n Peterson", R.drawable.mentor),Mentor("Jordan \n Peterson", R.drawable.mentor),Mentor("Jordan \n Peterson", R.drawable.mentor),Mentor("Jordan \n Peterson", R.drawable.mentor),Mentor("Jordan \n Peterson", R.drawable.mentor),Mentor("Jordan \n Peterson", R.drawable.mentor),Mentor("Jordan \n Peterson", R.drawable.mentor))
    btn_courseslist= mutableListOf(Btn_Courses("All",true),
        Btn_Courses("Business"), Btn_Courses("IT"), Btn_Courses("Philosophy"),
        Btn_Courses("Languages"))
        val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
       var adapter=OfferAdapter(requireContext(),offerlist)
        binding.rvOffer.adapter=adapter

        binding.rvOffer.layoutManager=layoutManager


       binding.rvMentors.adapter=MentorAdapter(requireContext(),mentorlist)
        binding.rvMentors.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        binding.rvBtnCourses.adapter=Btn_CoursesAdapter(requireContext(),btn_courseslist)
        binding.rvBtnCourses.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)



        return binding.root
    }

}