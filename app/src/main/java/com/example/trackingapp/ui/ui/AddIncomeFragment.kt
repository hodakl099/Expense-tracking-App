package com.example.trackingapp.ui.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.trackingapp.R
import com.example.trackingapp.databinding.FragmentAddIncomeBinding


class AddIncomeFragment : Fragment() {



    private lateinit var binding : FragmentAddIncomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentAddIncomeBinding.inflate(layoutInflater,container,false)

        val categories = resources.getStringArray(R.array.categories_array)
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_item,
            categories
        )
        binding.tvAutoCompleteText.setAdapter(arrayAdapter)

        return binding.root
    }


}