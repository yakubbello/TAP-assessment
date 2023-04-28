package com.example.tapassessment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tapassessment.adapter.ViewPagerAdapter
import com.example.tapassessment.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var bind: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentHomeBinding.inflate(inflater, container, false).run {
            bind = this
            root }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPagerWithTabLayout()
        addTabLayoutMediator()
    }


    private fun setUpViewPagerWithTabLayout() {
        bind.pager.adapter = ViewPagerAdapter(requireParentFragment(), listOfTitle)
    }

    private fun addTabLayoutMediator() {
        TabLayoutMediator(
            bind.tabLayout, bind.pager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = listOfTitle[position]
        }.attach()
    }

    companion object{
        val listOfTitle = listOf("Movies","Favorites")
    }

}