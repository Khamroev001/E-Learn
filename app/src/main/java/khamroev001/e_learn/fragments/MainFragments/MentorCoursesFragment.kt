package khamroev001.e_learn.fragments.MainFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import khamroev001.e_learn.adapter.CourseRecyclerAdapter
import khamroev001.e_learn.databinding.FragmentMentorCoursesBinding
import khamroev001.e_learn.utils.API
import khamroev001.e_learn.utils.AnimHelper
import khamroev001.e_learn.utils.Course
import khamroev001.e_learn.utils.Mentor


private const val ARG_PARAM1 = "param1"
class MentorCoursesFragment() : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val api = API.newInstance(requireContext())
        val binding = FragmentMentorCoursesBinding.inflate(inflater, container, false)
        binding.mentorCoursesRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.mentorCoursesRecycler.adapter = CourseRecyclerAdapter(api.getCourses(mentor!!), api, AnimHelper.newInstance(), requireContext(), object :CourseRecyclerAdapter.OnClick{
            override fun onPressed(course: Course) {
                val bundle = Bundle()
                bundle.putSerializable("param1", course)
//                findNavController().navigate(R.id.action_mentorFragment_to_courseDetailFragment, bundle)
            }

        })
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
            MentorCoursesFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }

}