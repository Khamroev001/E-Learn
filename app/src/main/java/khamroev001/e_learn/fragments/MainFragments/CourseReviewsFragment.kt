package khamroev001.e_learn.fragments.MainFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import khamroev001.e_learn.adapter.ReviewRecyclerAdapter
import khamroev001.e_learn.databinding.FragmentCourseReviewsBinding
import khamroev001.e_learn.utils.API
import khamroev001.e_learn.utils.Course


private const val ARG_PARAM1 = "param1"

class CourseReviewsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Course? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Course
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("TAG2",param1.toString())
        val binding = FragmentCourseReviewsBinding.inflate(inflater, container, false)
        val api = API.newInstance(requireContext())

        val adadpter = ReviewRecyclerAdapter(api.getReviews(param1!!))
        binding.courseReviewRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.courseReviewRecycler.adapter = adadpter
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: Course) =
            CourseReviewsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}