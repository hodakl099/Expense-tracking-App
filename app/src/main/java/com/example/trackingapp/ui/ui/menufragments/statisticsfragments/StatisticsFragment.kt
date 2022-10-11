package com.example.trackingapp.ui.ui.menufragments.statisticsfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.trackingapp.R
import com.example.trackingapp.databinding.FragmentStatisticsBinding
import com.example.trackingapp.ui.viewmodel.TransactionViewModel

private lateinit var binding : FragmentStatisticsBinding
private lateinit var transactionViewModel: TransactionViewModel

class StatisticsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val fragmentStatistics = FragmentStatisticsBinding.inflate(layoutInflater,container,false)

        transactionViewModel = ViewModelProvider(requireActivity())[TransactionViewModel::class.java]

        binding = fragmentStatistics
        return binding.root
    }

}