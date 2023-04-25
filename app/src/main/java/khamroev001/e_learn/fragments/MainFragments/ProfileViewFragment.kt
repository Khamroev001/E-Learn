package khamroev001.e_learn.fragments.MainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import khamroev001.e_learn.R
import khamroev001.e_learn.databinding.FragmentProfileBinding
import khamroev001.e_learn.databinding.FragmentProfileViewBinding
import khamroev001.e_learn.model.User
import khamroev001.e_learn.utils.API
import khamroev001.e_learn.utils.BottomDialogLogout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileViewFragment : Fragment() {
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
        // Inflate the layout for this fragment
        var binding=FragmentProfileViewBinding.inflate(inflater,container,false)
        var api= API.newInstance(requireContext())


       var user:User=api.getRegUser()

        binding.viewName.text=user.name
        binding.viewEmail.text=user.email

        if (api.getDialog()){
            api.setDialog("no")
            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        }

        binding.logout.setOnClickListener {
           showBottomSheet()
        }

        binding.editProfile.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_editProfileFragment)
        }


        return binding.root
    }
    private fun showBottomSheet() {
        val bottomSheetView = layoutInflater.inflate(R.layout.logout_bottom_dialog, null)
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        dialog.setContentView(bottomSheetView)

        val cancelButton: Button = bottomSheetView.findViewById(R.id.no)
        val logoutButton: Button = bottomSheetView.findViewById(R.id.yes)

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        logoutButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
            dialog.dismiss()
        }

        dialog.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileViewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}