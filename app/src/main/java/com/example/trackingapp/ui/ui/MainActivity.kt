package com.example.trackingapp.ui.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.trackingapp.R
import com.example.trackingapp.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var navController : NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_page -> {
                    replaceFragment(HomeFragment())
                }
                R.id.transaction_page-> {
                   replaceFragment(TransactionFragment())

                }
                R.id.add_page -> {
                   replaceFragment(AddIncomeFragment())
                }
                R.id.statistics_page -> {
                    replaceFragment(StatisticsFragment())
                }
                R.id.profile_page -> {
                    replaceFragment(SettingFragment())
                }

                else -> {
                    return@setOnItemSelectedListener false
                }

            }
            true
        }

        binding.bottomNavigationView.background = null

    }

       private fun replaceFragment(fragment: Fragment) {

       val fragmentManager =  supportFragmentManager
       val fragmentTransaction = fragmentManager.beginTransaction()
       fragmentTransaction.replace(R.id.frame_layout,fragment)
       fragmentTransaction.commit()


    }
}