package com.example.trackingapp.ui.ui

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.trackingapp.R
import com.example.trackingapp.databinding.ActivityMainBinding
import com.example.trackingapp.ui.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var tabLayout: TabLayout

    private lateinit var viewPager2: ViewPager2

    private val fragmentExpense = ExpenseFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNavigationView.background = null



        tabLayout = binding.tabLayout
        viewPager2 = binding.pager

        binding.pager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(tabLayout,viewPager2){tab,index ->
            tab.text = when(index) {
                0 -> {"Income"}
                1 -> {"Expense"}
                else -> throw Resources.NotFoundException("Not found")
            }
        }.attach()

    }
}