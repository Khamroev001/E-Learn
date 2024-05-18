package khamroev001.e_learn.fragments.MainFragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.api.load
import com.google.android.material.tabs.TabLayoutMediator
import khamroev001.e_learn.R
import khamroev001.e_learn.adapter.MyViewPagerAdapter
import khamroev001.e_learn.adapter.ViewPagerAdapter
import khamroev001.e_learn.databinding.FragmentMentorBinding
import khamroev001.e_learn.utils.API
import khamroev001.e_learn.utils.AnimHelper
import khamroev001.e_learn.utils.Mentor

private const val ARG_PARAM1 = "param1"

class MentorFragment : Fragment() {


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val api = API.newInstance(requireContext())
        val animHelper = AnimHelper.newInstance()
        val binding = FragmentMentorBinding.inflate(layoutInflater, container, false)

        val viewPager = binding.courseViewPager
        viewPager.adapter = ViewPagerAdapter(
            childFragmentManager,
            lifecycle,
            arrayListOf(MentorCoursesFragment.newInstance(mentor!!), MentorStudentsFragment.newInstance(mentor!!), MentorReviewsFragment.newInstance(mentor!!))
        )

        val tabLayout = binding.courseTabLayout

        TabLayoutMediator(tabLayout, viewPager){tab, position->
            when (position){
                0->{
                    tab.text = "Courses"
                }
                1->{
                    tab.text = "Students"
                }
                else->{
                    tab.text = "Reviews"
                }
            }
        }.attach()




        binding.mentorImage.load(mentor!!.img)
        binding.mentorName.text = mentor!!.firstName + " " + mentor!!.lastName
        binding.mentorJob.text = mentor!!.job
        binding.courseNum.text = api.getCourses(mentor!!).size.toString()
        binding.studentsNum.text = api.getStudentsCount(mentor!!).toString()
        binding.reviewsNum.text = api.getReviews(mentor!!).size.toString()

        binding.mentorBackArrow.setOnClickListener {
            animHelper.animate(requireContext(), binding.mentorBackArrow, R.anim.button_press_anim, object :AnimHelper.EndAction{
                override fun endAction() {
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }

            })
        }
        binding.mentorMore.setOnClickListener {
            animHelper.animate(requireContext(), binding.mentorMore, R.anim.button_press_anim, object : AnimHelper.EndAction{
                override fun endAction() {

                }

            })
        }

        return binding.root
    }


    private var mentor: Mentor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mentor = it.getSerializable(ARG_PARAM1) as Mentor
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: Mentor) =
            MentorFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}