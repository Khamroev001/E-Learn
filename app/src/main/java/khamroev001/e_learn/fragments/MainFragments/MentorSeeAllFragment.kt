package khamroev001.e_learn.fragments.MainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import khamroev001.e_learn.R
import khamroev001.e_learn.adapter.MentorSeeAllRecyclerAdapter
import khamroev001.e_learn.databinding.FragmentMentorSeeAllBinding
import khamroev001.e_learn.utils.API
import khamroev001.e_learn.utils.AnimHelper
import khamroev001.e_learn.utils.Mentor

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CourseSeeAll.newInstance] factory method to
 * create an instance of this fragment.
 */
class MentorSeeAllFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding=FragmentMentorSeeAllBinding.inflate(inflater,container,false)

        var api:API=API.newInstance(requireContext())
        var animHelper=AnimHelper.newInstance()

        binding.mentorSeeallRv.adapter=MentorSeeAllRecyclerAdapter(api.getMentors(),animHelper,requireContext(), object : MentorSeeAllRecyclerAdapter.OnPressed{
            override fun onPressed(mentor: Mentor) {
                val bundle = Bundle()
                bundle.putSerializable("param1", mentor)
                findNavController().navigate(R.id.action_mentorSeeAllFragment_to_mentorFragment, bundle)
            }

        })
        binding.mentorSeeallRv.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)









        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CourseSeeAll.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CourseSeeAll().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}