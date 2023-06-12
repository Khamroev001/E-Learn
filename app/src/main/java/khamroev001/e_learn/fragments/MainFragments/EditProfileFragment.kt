package khamroev001.e_learn.fragments.MainFragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.DatePicker
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import khamroev001.e_learn.R
import khamroev001.e_learn.adapter.GenderAdapter
import khamroev001.e_learn.databinding.FragmentEditProfileBinding
import khamroev001.e_learn.model.User
import khamroev001.e_learn.utils.API
import java.text.SimpleDateFormat
import java.util.*


class EditProfileFragment : Fragment() {
lateinit var binding:FragmentEditProfileBinding
lateinit var api:API
lateinit var user: User
lateinit var selectedGender:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding=FragmentEditProfileBinding.inflate(inflater,container,false)


        api=API.newInstance(requireContext())


        user=api.getRegUser()!!


        binding.birthdate.setHint(user.birthdate)

        binding.birthdate.setOnClickListener {
            showDatePickerDialog()
        }
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.fullName.hint=user.name
        binding.username.hint=user.username

        selectedGender=""
        val genders = resources.getStringArray(R.array.gender_options)
        val adapter = GenderAdapter(requireActivity(), android.R.layout.simple_spinner_item, genders)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
        for (i in 0..genders.size-1){
            if (user.gender==genders[i]){
                binding.spinner.setSelection(i)
                selectedGender = genders[i].toString()
            }
        }

        binding.btnUpdate.setOnClickListener {
          showBottomSheet()
        }

        // Set an item selected listener to handle the selected gender
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedGender = parent?.getItemAtPosition(position).toString()
                // Handle the selected gender as needed
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                for (i in 0..genders.size){
                    if (user.gender==genders[i]){
                        selectedGender = genders[i].toString()
                        return
                    }
                }
            }
        }

        binding.phonenumber.hint=user.phonenumber


// Select the second item in the Spinner



        return binding.root
    }

    @SuppressLint("MissingInflatedId")
    private fun showBottomSheet() {
        val bottomSheetView = layoutInflater.inflate(R.layout.update_bottom_dialog, null)
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        dialog.setContentView(bottomSheetView)

        val cancelButton: Button = bottomSheetView.findViewById(R.id.no)
        val updateButton: Button = bottomSheetView.findViewById(R.id.yes)

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        updateButton.setOnClickListener {
            var users=api.getRegisteredUsersList()
            println(users!!.joinToString())
            println(user.name)
            println(user)
            if (users != null) {
                for (i in 0..users.size-1){
                    if (users[i]==user){
                        println("AAAAAAAAAAAAAAAAAAAAAA")
                        users.remove(user)
                        var name:String=binding.fullName.text.toString()
                        var username:String=binding.username.text.toString()
                        var phonenumber:String=binding.phonenumber.text.toString()
                        var birthdate:String=binding.birthdate.text.toString()

                        if (binding.fullName.text.toString()==""){
                            name=binding.fullName.hint.toString()
                        }
                        if (binding.username.text.toString()==""){
                            username=binding.username.hint.toString()
                        }
                        if (binding.phonenumber.text.toString()==""){
                            phonenumber=binding.phonenumber.hint.toString()
                        }
                        if (binding.birthdate.text.toString()==""){
                            birthdate=binding.birthdate.hint.toString()
                        }
                        var new_user=User(user.email,user.password,name,username,birthdate,phonenumber,selectedGender,user.pin)
                        users.add(new_user)
                        api.setRegUser(new_user)
                        api.setRegisteredUsersList(users)
                    }
                }
            }

            dialog.dismiss()
        }

        dialog.show()
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