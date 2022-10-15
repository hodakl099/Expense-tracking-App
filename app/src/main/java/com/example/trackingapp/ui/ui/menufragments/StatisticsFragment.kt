package com.example.trackingapp.ui.ui.menufragments

import android.content.res.Resources.NotFoundException
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.trackingapp.databinding.FragmentStatisticsBinding
import com.example.trackingapp.ui.adapters.ViewPagerAdapter
import com.example.trackingapp.ui.viewmodel.TransactionViewModel
import com.google.android.material.tabs.TabLayoutMediator



class StatisticsFragment : Fragment() {


    private lateinit var binding : FragmentStatisticsBinding

    private lateinit var transactionViewModel: TransactionViewModel

    private lateinit var adapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
// Inflate the layout for this fragment

    val fragmentStatistics = FragmentStatisticsBinding.inflate(layoutInflater,container,false)

    transactionViewModel = ViewModelProvider(requireActivity())[TransactionViewModel::class.java]

    binding = fragmentStatistics
    setUpTabs()
    return binding.root
}

private fun setUpTabs() {
    adapter = ViewPagerAdapter(this)
    binding.viewPager.adapter = adapter
    TabLayoutMediator(binding.tabs, binding.viewPager){tab,index ->
        tab.text = when(index) {
            0 -> "Expense"
            1 -> "Income"
            else -> throw NotFoundException("index is not found!")
        }
    }.attach()
}

}