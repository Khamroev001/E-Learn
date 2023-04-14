package khamroev001.e_learn.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import khamroev001.e_learn.R
import khamroev001.e_learn.databinding.FragmentProfileBinding
import java.text.SimpleDateFormat
import java.util.*


class ProfileFragment : Fragment() {

     lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentProfileBinding.inflate(inflater,container,false)


        var mycalendar=Calendar.getInstance()

        var datePicker=DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            mycalendar.set(Calendar.YEAR,year)
            mycalendar.set(Calendar.MONTH,monthOfYear)
            mycalendar.set(Calendar.DAY_OF_MONTH,year)
            updateLabel(mycalendar)
        }


        binding.birthdate.setOnClickListener {
            DatePickerDialog(requireActivity(),datePicker,mycalendar.get(Calendar.YEAR),mycalendar.get(Calendar.MONTH),mycalendar.get(Calendar.DAY_OF_MONTH)).show()

        }

        return binding.root
    }

    private fun updateLabel(mycalendar: Calendar) {
          var myformat="dd/MM/yyyy"
        val sdf=SimpleDateFormat(myformat, Locale.ENGLISH)
        binding.birthdate.setText(sdf.format(mycalendar.time))
    }

}