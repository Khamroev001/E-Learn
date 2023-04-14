package khamroev001.e_learn.fragments

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import khamroev001.e_learn.R
import khamroev001.e_learn.databinding.FragmentCreateAccBinding
import khamroev001.e_learn.model.User


class CreateAccFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding:FragmentCreateAccBinding= FragmentCreateAccBinding.inflate(inflater,container,false)


        var usersList = mutableListOf<User>()
        var sh= activity?.getSharedPreferences("user", Context.MODE_PRIVATE)!!
        var type = object : TypeToken<List<User>>() {}.type
        var gson = Gson()
        var edit = sh.edit()

        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }


        binding.createAcc.setOnClickListener {
            println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
            var str = sh.getString("user", "")
            usersList = if (str == "") {
                mutableListOf<User>()
            } else {
                gson.fromJson(str, type)
            }


            if (binding.passwordUp.text.toString().length < 6) {
                Toast.makeText(activity, "password should include more than 6 characters",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailUp.text).matches()) {
                    Toast.makeText(activity, "your email is not valid", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    usersList.add(User(binding.emailUp.text.toString(), binding.passwordUp.text.toString()))
                    var s = gson.toJson(usersList)
                    edit.putString("user", s).apply()

                    findNavController().navigate(R.id.action_createAccFragment_to_profileFragment)
                }
            }
        }
        binding.loginPage.setOnClickListener{
            findNavController().navigate(R.id.action_createAccFragment_to_loginFragment)
        }




            return binding.root
    }


}