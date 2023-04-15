package khamroev001.e_learn.fragments

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.DatePicker
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import khamroev001.e_learn.R
import khamroev001.e_learn.adapter.GenderAdapter
import khamroev001.e_learn.databinding.FragmentProfileBinding
import khamroev001.e_learn.model.User
import java.text.SimpleDateFormat
import java.util.*


class ProfileFragment : Fragment() {

     lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentProfileBinding.inflate(inflater,container,false)





        binding.birthdate.setOnClickListener {
          showDatePickerDialog()
        }

        var email=arguments?.getString("email")
        println(email)
        var selectedGender:String=""


        // Create an instance of GenderAdapter and set it as the adapter for the spinner
        val genders = resources.getStringArray(R.array.gender_options)
        val adapter = GenderAdapter(requireActivity(), android.R.layout.simple_spinner_item, genders)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter


        // Set an item selected listener to handle the selected gender
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedGender = parent?.getItemAtPosition(position).toString()
                // Handle the selected gender as needed
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedGender = genders[0].toString()
            }

        }



        var sh= activity?.getSharedPreferences("user", Context.MODE_PRIVATE)!!
        var type = object : TypeToken<List<User>>() {}.type
        var gson = Gson()
        var edit = sh.edit()
        var str = sh.getString("user", "")
        var usersList = gson.fromJson<MutableList<User>>(str,type)
     binding.btnContinue.setOnClickListener{

         for(i in usersList){
             println(i.email)
             println(email)
             if(i.email==email ){
                 var password=i.password
                 usersList.remove(i)
                 usersList.add(User(email,password,binding.fullName.text.toString(),binding.username.text.toString(),binding.birthdate.text.toString(),binding.phonenumber.text.toString(),selectedGender))
                 var s = gson.toJson(usersList)
                 edit.putString("user", s).apply()
                 findNavController().navigate(R.id.action_profileFragment_to_pinFragment)
             }
         }

     }

        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }






        return binding.root

    }


    private fun showDatePickerDialog() {
        var selectedDate: Calendar? = null
        val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        // Create a DatePickerDialog and set its callback
        val datePickerDialog = DatePickerDialog(
            requireActivity(),
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                selectedDate = Calendar.getInstance()
                selectedDate?.set(Calendar.YEAR, year)
                selectedDate?.set(Calendar.MONTH, month)
                selectedDate?.set(Calendar.DAY_OF_MONTH, dayOfMonth)
              binding.birthdate.setText(dateFormatter.format(selectedDate?.time))
            },
            year,
            month,
            dayOfMonth
        )

        // Show the DatePickerDialog
        datePickerDialog.show()
    }



}