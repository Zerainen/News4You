package com.example.news4you.overview

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class NewsFragmentViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val categories: List<String> = listOf("general", "science", "entertainment", "sports", "technology")

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        return NewsFragment(categories[position])
    }

}