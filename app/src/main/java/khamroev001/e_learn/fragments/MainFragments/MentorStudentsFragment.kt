package khamroev001.e_learn.fragments.MainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import khamroev001.e_learn.R
import khamroev001.e_learn.utils.Mentor


private const val ARG_PARAM1 = "param1"

class MentorStudentsFragment : Fragment() {
    private var param1: Mentor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Mentor
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mentor_students, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: Mentor) =
            MentorStudentsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}