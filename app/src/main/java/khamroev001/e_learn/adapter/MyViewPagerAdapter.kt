package khamroev001.e_learn.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import khamroev001.e_learn.fragments.MainFragments.CourseAboutFragment
import khamroev001.e_learn.fragments.MainFragments.CourseReviewsFragment
import khamroev001.e_learn.utils.API
import khamroev001.e_learn.utils.Course




class MyViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    var course: Course,
    api: API
):FragmentStateAdapter(fragmentManager, lifecycle) {

    val fragmentsList= arrayListOf(CourseAboutFragment.newInstance(course,api),CourseReviewsFragment.newInstance(course))


    override fun getItemCount(): Int {
        return fragmentsList.size
    }

    override fun createFragment(position: Int): Fragment {
         return fragmentsList[position]
    }
}