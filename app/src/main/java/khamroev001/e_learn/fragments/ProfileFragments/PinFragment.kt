package khamroev001.e_learn.fragments.ProfileFragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import khamroev001.e_learn.R
import khamroev001.e_learn.databinding.FragmentPinBinding
import khamroev001.e_learn.model.User


class PinFragment : Fragment(),View.OnClickListener {
      lateinit var binding: FragmentPinBinding
    var clickcount = 0
    var pin = ""
   lateinit var email:String
    lateinit var sh: SharedPreferences
    lateinit var edit: SharedPreferences.Editor
    lateinit var gson: Gson
    lateinit var str:String
    lateinit var usersList: MutableList<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding=FragmentPinBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment


         sh= activity?.getSharedPreferences("user", Context.MODE_PRIVATE)!!
        var type = object : TypeToken<List<User>>() {}.type
        gson = Gson()
        edit = sh.edit()
        str = sh.getString("user", "").toString()
        usersList = gson.fromJson<MutableList<User>>(str,type)
        email= arguments?.getString("email").toString()

        binding.dot1.setOnClickListener(this)
        binding.dot2.setOnClickListener(this)
        binding.dot3.setOnClickListener(this)
        binding.dot4.setOnClickListener(this)
        binding.dot5.setOnClickListener(this)
        binding.dot6.setOnClickListener(this)
        binding.dot7.setOnClickListener(this)
        binding.dot8.setOnClickListener(this)
        binding.dot9.setOnClickListener(this)
        binding.dot0.setOnClickListener(this)


        binding.delete.setOnClickListener {
            pin.dropLast(0)
            if (clickcount == 1) {
                binding.plot1.setBackgroundResource(R.drawable.cube_border)
                clickcount--
            }
            if (clickcount == 2) {
                binding.plot2.setBackgroundResource(R.drawable.cube_border)
                clickcount--
            }
            if (clickcount == 3) {
                binding.plot3.setBackgroundResource(R.drawable.cube_border)
                clickcount--
            }
            if (clickcount == 4) {
                binding.plot4.setBackgroundResource(R.drawable.cube_border)
                clickcount--
            }
        }
        binding.logOut.setOnClickListener {
        }
        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return binding.root
    }

    override fun onClick(p0: View?) {
        var number = requireActivity().findViewById<Button>(p0!!.id)
        clickcount++
        if (clickcount == 1) {
            binding.plot1.setBackgroundResource(R.drawable.cube_border_filled)
            pin += number.text
        }
        if (clickcount == 2) {
            binding.plot2.setBackgroundResource(R.drawable.cube_border_filled)
            pin += number.text
        }
        if (clickcount == 3) {
            binding.plot3.setBackgroundResource(R.drawable.cube_border_filled)
            pin += number.text
        }
        if (clickcount == 4) {
            binding.plot4.setBackgroundResource(R.drawable.cube_border_filled)
            pin += number.text
            stop()

        }
        if (clickcount >= 4) {
            for (i in usersList){
                if (i.email==email){
                    println(i.pin)
                    if (i.pin==0){
                        usersList.add(User(i.email,i.password,i.name,i.username,i.birthdate,i.phonenumber,i.gender,pin.toInt()))
                        usersList.remove(i)
                        var s = gson.toJson(usersList)
                        edit.putString("user", s).apply()
                        findNavController().navigate(R.id.action_pinFragment_to_mainFragment)
                    }else{
                        if (pin.toInt()==i.pin){
                            binding.plot1.setBackgroundResource(R.drawable.cube_green)
                            binding.plot2.setBackgroundResource(R.drawable.cube_green)
                            binding.plot3.setBackgroundResource(R.drawable.cube_green)
                            binding.plot4.setBackgroundResource(R.drawable.cube_green)
                            Handler(Looper.getMainLooper()).postDelayed({
                                pin = ""
                                clickcount = 0
                               findNavController().navigate(R.id.action_pinFragment_to_mainFragment)
                            }, 500)
                    } else{
                            pin = ""
                            clickcount = 0
                            restart()
                    }
            }
                }
            }

        }
    }


    fun stop() {
        binding.dot1.isClickable = false
        binding.dot2.isClickable = false
        binding.dot3.isClickable = false
        binding.dot4.isClickable = false
        binding.dot5.isClickable = false
        binding.dot6.isClickable = false
        binding.dot7.isClickable = false
        binding.dot8.isClickable = false
        binding.dot9.isClickable = false
        binding.dot0.isClickable = false
    }

    fun restart() {
        Toast.makeText(requireActivity(),"Incorrect PIN",Toast.LENGTH_SHORT).show()
        binding.dot1.isClickable = true
        binding.dot2.isClickable = true
        binding.dot3.isClickable = true
        binding.dot4.isClickable = true
        binding.dot5.isClickable = true
        binding.dot6.isClickable = true
        binding.dot7.isClickable = true
        binding.dot8.isClickable = true
        binding.dot9.isClickable = true
        binding.dot0.isClickable = true

        binding.plot1.setBackgroundResource(R.drawable.cube_red)
        binding.plot2.setBackgroundResource(R.drawable.cube_red)
        binding.plot3.setBackgroundResource(R.drawable.cube_red)
        binding.plot4.setBackgroundResource(R.drawable.cube_red)

        stop()

        Handler(Looper.getMainLooper()).postDelayed({
            binding.plot1.setBackgroundResource(R.drawable.cube_border)
            binding.plot2.setBackgroundResource(R.drawable.cube_border)
            binding.plot3.setBackgroundResource(R.drawable.cube_border)
            binding.plot4.setBackgroundResource(R.drawable.cube_border)
        }, 500)

        binding.dot1.isClickable = true
        binding.dot2.isClickable = true
        binding.dot3.isClickable = true
        binding.dot4.isClickable = true
        binding.dot5.isClickable = true
        binding.dot6.isClickable = true
        binding.dot7.isClickable = true
        binding.dot8.isClickable = true
        binding.dot9.isClickable = true
        binding.dot0.isClickable = true
    }



    }