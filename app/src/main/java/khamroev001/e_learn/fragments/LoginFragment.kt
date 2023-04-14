package khamroev001.e_learn.fragments

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import khamroev001.e_learn.R
import khamroev001.e_learn.databinding.FragmentLoginBinding
import khamroev001.e_learn.model.User


class LoginFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding=FragmentLoginBinding.inflate(inflater,container,false)



        var usersList= mutableListOf<User>()

        var type = object : TypeToken<List<User>>() {}.type
        var gson = Gson()
        var sh = requireActivity().getSharedPreferences("user", MODE_PRIVATE)
        var edit = sh.edit()

        binding.login.setOnClickListener {

            var str = sh.getString("user", "")
            usersList = if (str == "") {
                mutableListOf<User>()
            } else {
                gson.fromJson(str, type)
            }

            for (i in usersList) {
                if (binding.emailUp.text.toString() == i.email) {
                    if (i.password == binding.passwordUp.text.toString()) {
                      findNavController().navigate(R.id.action_loginFragment_to_pinFragment)
                    }else {
                        Toast.makeText(activity, "Password or email is incorrect", Toast.LENGTH_LONG).show()
                    }
                }
            }





    }
        binding.back.setOnClickListener {
            activity?.onBackPressed()
        }
        binding.signupPage.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_createAccFragment)
        }
        return binding.root

}
}

