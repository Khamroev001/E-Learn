package khamroev001.e_learn.fragments.MainFragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.api.load
import khamroev001.e_learn.databinding.FragmentCourseAboutBinding
import khamroev001.e_learn.utils.Course


private const val ARG_PARAM1 = "param1"
class CourseAboutFragment : Fragment() {
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
        Log.d("TAG", param1.toString())
        val binding = FragmentCourseAboutBinding.inflate(inflater, container, false)
        binding.courseDetailAboutCourse.text = param1!!.about
        binding.courseDetailEnrollButtton.text = "Enroll course - ${param1!!.prices.last()}$"
        binding.mentorImage.load(param1!!.mentor.img)
        binding.courseDetailMentorJob.text = param1!!.mentor.job
        binding.courseDetailMentorName.text = param1!!.mentor.firstName + " " + param1!!.mentor.firstName
        Log.d("TAG", "onCreateView:  About")
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Course) =
            CourseAboutFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}