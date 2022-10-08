package com.example.trackingapp.ui.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trackingapp.R
import com.example.trackingapp.databinding.FragmentDetialTransactionBinding

class DetailTransactionFragment : Fragment() {



    private lateinit var binding: FragmentDetialTransactionBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val detailTransactionBinding = FragmentDetialTransactionBinding.inflate(layoutInflater)
        binding = detailTransactionBinding
        return binding.root
    }

}