package com.example.trackingapp.ui.adapters

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.trackingapp.ui.ui.menufragments.ExpenseStatisticsFragment
import com.example.trackingapp.ui.ui.menufragments.IncomeStatisticsFragment

class ViewPagerAdapter(fragment: Fragment) :  FragmentStateAdapter(fragment){



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