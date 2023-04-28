package com.example.tapassessment.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tapassessment.ui.FavoriteFragment
import com.example.tapassessment.ui.MovieFragment

class ViewPagerAdapter(fa: Fragment, private val titleList:List<String>):
    FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = titleList.size

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return MovieFragment()
            1 -> return FavoriteFragment()
        }
        return MovieFragment()
    }
}