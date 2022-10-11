package com.example.trackingapp.ui.adapters

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.trackingapp.ui.ui.menufragments.statisticsfragments.ExpenseStatisticsFragment
import com.example.trackingapp.ui.ui.menufragments.statisticsfragments.IncomeStatisticsFragment

class ViewPagerAdapter(fragment: Fragment, val mFragmentList: Array<String>, val viewPager: ViewPager2) :  FragmentStateAdapter(fragment){
    private val fragmentList = ArrayList<Fragment>()
    private val fragmentTitleList = ArrayList<String>()

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
       return when(position) {
            0 -> ExpenseStatisticsFragment()
            1 -> IncomeStatisticsFragment()
            else -> {
                throw Resources.NotFoundException("position not found")
            }
        }
    }
}