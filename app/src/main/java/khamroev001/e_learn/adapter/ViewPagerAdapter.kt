package khamroev001.e_learn.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import khamroev001.e_learn.fragments.MainFragments.CourseAboutFragment
import khamroev001.e_learn.fragments.MainFragments.CourseReviewsFragment
import khamroev001.e_learn.utils.API
import khamroev001.e_learn.utils.Course




class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
     var fragmentsList: List<Fragment>
):FragmentStateAdapter(fragmentManager, lifecycle) {
        override fun getItemCount(): Int {
        return fragmentsList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentsList[position]
    }
}