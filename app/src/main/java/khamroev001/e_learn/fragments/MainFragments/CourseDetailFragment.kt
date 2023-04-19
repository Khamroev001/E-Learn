package khamroev001.e_learn.fragments.MainFragments

import android.annotation.SuppressLint
import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import coil.api.load
import com.google.android.material.tabs.TabLayoutMediator
import khamroev001.e_learn.R
import khamroev001.e_learn.adapter.MyViewPagerAdapter
import khamroev001.e_learn.databinding.FragmentCourseDEtailBinding
import khamroev001.e_learn.utils.API
import khamroev001.e_learn.utils.AnimHelper
import khamroev001.e_learn.utils.Course


private const val ARG_PARAM1 = "param1"

class CourseDetailFragment : Fragment() {
    private var param1: Course? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Course
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val animHelper = AnimHelper.newInstance()
        val api = API.newInstance(requireContext())
        val binding = FragmentCourseDEtailBinding.inflate(inflater, container, false)
        var hour = param1!!.durationHour.toString()
        val k = (param1!!.durationMin*10/60)
        if (k > 0){
            hour += ".${k}"
        }
        hour += " hours"

        binding.courseDetailCategory.text = param1!!.category.namE
        binding.courseDetailHours.text = hour
        binding.courseDetailImage.load(param1!!.image)
        binding.mentorName.text = param1!!.name
        binding.courseDetailRating.text = api.getRating(param1!!, api.getReviews()).toString().substring(0, 3)

        if (!param1!!.hasCertificate){
            binding.courseDetailGivesCertificate.visibility = View.INVISIBLE
        }
        val reviews = api.getReviews(param1!!)
        binding.courseDetailReviewCount.text = "(${api.getReviews(param1!!).size} reviews)"
        if (reviews.isEmpty()){
            binding.courseDetailReviewCount.text = "(${api.getReviews(param1!!).size} reviews)"
        }
        binding.courseDetailPrice.text = "$" + param1!!.prices.last().toString()
        if (param1!!.prices.size > 1) {
            binding.courseDetailOldPrice.text = "$" + param1!!.prices[param1!!.prices.size-2].toString()
            binding.courseDetailOldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            binding.courseDetailOldPrice.visibility = View.INVISIBLE
        }
        if (api.getBookmarks().contains(param1!!)){
            binding.homeCourseItemBookmark.setImageResource(R.drawable.bookmark_selected)
        }
        binding.courseDetailStudentsCount.text = api.getStudentsCount(param1!!).toString() + " students"
        binding.homeCourseItemBookmark.setOnClickListener {
            var res = R.drawable.bookmark
            if (api.updateBookmarks(param1!!)){
                res = R.drawable.bookmark_selected
                animHelper.animate(requireContext(), binding.homeCourseItemBookmark, R.anim.bookmarked_anim_part1, object : AnimHelper.EndAction{
                    override fun endAction() {
                        binding.homeCourseItemBookmark.setImageResource(res)
                        val anim  = AnimationUtils.loadAnimation(context, R.anim.bookmarked_anim_part2)
                        binding.homeCourseItemBookmark.startAnimation(anim)
                    }

                })
            }else{
                // TODO : add alert dialog
                animHelper.animate(requireContext(), binding.homeCourseItemBookmark, R.anim.unbookmarked_anim_part1, object : AnimHelper.EndAction{
                    override fun endAction() {
                        binding.homeCourseItemBookmark.setImageResource(res)
                        val anim  = AnimationUtils.loadAnimation(context, R.anim.unbookmarked_anim_part2)
                        binding.homeCourseItemBookmark.startAnimation(anim)
                    }

                })
            }
        }
        binding.courseDetailBackArrow.setOnClickListener {
            animHelper.animate(requireContext(), binding.courseDetailBackArrow, R.anim.button_press_anim, object :
                AnimHelper.EndAction{
                override fun endAction() {
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }

            })
        }

        val viewPager = binding.courseViewPager
        viewPager.adapter = MyViewPagerAdapter(parentFragmentManager, lifecycle, arrayListOf(
            CourseAboutFragment.newInstance(param1!!), CourseReviewsFragment.newInstance(param1!!)))
        val tabLayout = binding.courseTabLayout

        TabLayoutMediator(tabLayout, viewPager){tab, position->
            when (position){
                0->{
                    tab.text = "About"
                }
                else->{
                    tab.text = "Reviews"
                }
            }
        }.attach()

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: Course) =
            CourseDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}