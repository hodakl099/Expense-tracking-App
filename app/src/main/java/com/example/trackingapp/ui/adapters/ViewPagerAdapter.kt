package com.example.trackingapp.ui.adapters


import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.trackingapp.ui.ui.IncomeFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
         return  when(position) {
              0 -> IncomeFragment()
              1 -> ExpenseFragment()
              else -> throw Resources.NotFoundException("position Not Found!")
          }

    }
}