package com.example.trackingapp.ui.ui.menufragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.trackingapp.databinding.FragmentCalendarBinding

import com.example.trackingapp.ui.viewmodel.TransactionViewModel

private lateinit var binding: FragmentCalendarBinding

class CalendarFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

      val  calendarFragment = FragmentCalendarBinding.inflate(layoutInflater)
         binding = calendarFragment
        return binding.root
    }
}


//{
    // Inflate the layout for this fragment

//    val fragmentStatistics = FragmentStatisticsBinding.inflate(layoutInflater,container,false)
//
//    transactionViewModel = ViewModelProvider(requireActivity())[TransactionViewModel::class.java]
//
//    com.example.trackingapp.ui.ui.menufragments.statisticsfragments.binding = fragmentStatistics
//    setUpTabs()
//    return com.example.trackingapp.ui.ui.menufragments.statisticsfragments.binding.root
//}
//
//private fun setUpTabs() {
//
//    adapter = ViewPagerAdapter(this)
//    com.example.trackingapp.ui.ui.menufragments.statisticsfragments.binding.viewPager.adapter = adapter
//
//
//}
//
//}