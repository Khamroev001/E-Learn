package khamroev001.e_learn.fragments.MainFragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import khamroev001.e_learn.R
import khamroev001.e_learn.databinding.FragmentMainBinding

class MainFragment : Fragment() {

lateinit var binding: FragmentMainBinding
lateinit var email:String
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMainBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment


      email= arguments?.getString("email").toString()






        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    bundleOf().putString("email",email)
                    childFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,MainFragment()).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_mycourse -> {
                    childFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,MyCoursesFragment()).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_inbox -> {
                    childFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,InboxFragment()).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_profile -> {
                    childFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,ProfileViewFragment()).commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_cart -> {
                    childFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,CartFragment()).commit()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }


















        return binding.root
    }
    companion object {
        fun newInstance(data: String): MainFragment {
            val fragment = MainFragment()
            val args = Bundle()
            args.putString("email", data)
            fragment.arguments = args
            return fragment
        }
    }

}