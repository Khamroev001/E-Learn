package khamroev001.e_learn.fragments.MainFragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import khamroev001.e_learn.CategoryRecyclerAdapter
import khamroev001.e_learn.R
import khamroev001.e_learn.adapter.*
import khamroev001.e_learn.databinding.FragmentHomeBinding
import khamroev001.e_learn.model.Btn_Courses
import khamroev001.e_learn.model.Discount_offers
import khamroev001.e_learn.utils.*


class HomeFragment : Fragment() {

  lateinit var binding: FragmentHomeBinding
   lateinit var offerlist:MutableList<Discount_offers>
   lateinit var mentorlist:MutableList<Mentor>
   lateinit var btn_courseslist:MutableList<Btn_Courses>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var api = API.newInstance(requireContext())
        var animHelper= AnimHelper.newInstance()

        binding= FragmentHomeBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment

    offerlist= mutableListOf(Discount_offers(40,"Today's special","Get discount for every course order",Color.BLUE),Discount_offers(30,"Today's special","Get discount for every course order",Color.RED),Discount_offers(40,"Today's special","Get discount for every course order",Color.LTGRAY),Discount_offers(70,"Today's special","Get discount for every course order",Color.MAGENTA),Discount_offers(20,"Today's special","Get discount for every course order",Color.parseColor("#466DFA")))
      btn_courseslist= mutableListOf(Btn_Courses("All",true),
        Btn_Courses("Business"), Btn_Courses("IT"), Btn_Courses("Philosophy"),
        Btn_Courses("Languages"))
        val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
       var adapter=OfferAdapter(requireContext(),offerlist)
        binding.rvOffer.adapter=adapter

        binding.rvOffer.layoutManager=layoutManager


        println(api.getMentors().joinToString())
        println(api.getCourses().joinToString())


        binding.rvMentors.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvMentors.adapter =
            MentorsRecyclerAdapter(api.getMentors(), animHelper, requireContext(), object :MentorsRecyclerAdapter.OnPressed{
                override fun onPressed(mentor: Mentor) {
                    val bundle = Bundle()
                    bundle.putSerializable("param1", mentor)
//                    findNavController().navigate(R.id.action_bodyFragment_to_mentorFragment, bundle)
                }
            })

      println(api.getMentors().joinToString())
      println(api.getCourses().joinToString())
        binding.coursesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val courseAdapter =
            CourseRecyclerAdapter(api.getCourses(), api, animHelper, requireContext(), object : CourseRecyclerAdapter.OnClick{
                override fun onPressed(course: Course) {
                    val bundle = Bundle()
                    bundle.putSerializable("param1", course)
//                    findNavController().navigate(R.id.action_bodyFragment_to_courseDetailFragment, bundle)
                }

            })
        binding.coursesRecyclerView.adapter = courseAdapter


        binding.coursesRecyclerView.adapter = courseAdapter




        binding.categoryRecyclerView.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.categoryRecyclerView.adapter =
            CategoryRecyclerAdapter(object : CategoryRecyclerAdapter.OnCategoryChanged {
                @SuppressLint("NotifyDataSetChanged")
                override fun onCategoryChanged(category: Category?) {
                    val new = if (category == null) {
                        api.getCourses()
                    } else {
                        api.getCourses(category)
                    }
                    courseAdapter.courses = new
                    courseAdapter.notifyDataSetChanged()
                }
            })


        return binding.root
    }

}