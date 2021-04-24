package com.example.news4you.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.news4you.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class CollectionNewsFragments : Fragment() {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    private lateinit var newsFragmentViewPagerAdapter: NewsFragmentViewPagerAdapter
    private lateinit var viewPager: ViewPager2
    private val categories: List<String> = listOf("Allgemein", "Wissenschaft", "Entertainment", "Sport", "Technologie")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_collection_news_fragments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        newsFragmentViewPagerAdapter = NewsFragmentViewPagerAdapter(this)
        viewPager = view.findViewById(R.id.view_pager_2)
        viewPager.adapter = newsFragmentViewPagerAdapter

        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = categories[position]
        }.attach()
    }

}