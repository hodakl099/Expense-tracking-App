package com.example.trackingapp.ui.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trackingapp.R
import com.example.trackingapp.databinding.FragmentHomeBinding
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding



    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(layoutInflater, container, false)


        if (arguments == null) {
           binding.tvAmountExpense.text = "$00.0"
        } else if(requireArguments().containsKey("expenseText")) {

            val valueText = requireArguments().get("expenseText").toString()

            val currency = Currency.getInstance("USD")

            val symbol = currency.symbol

            val currencyValue = "$${valueText}"

            binding.tvAmountExpense.text = currencyValue


//            binding.tvAmountExpense.text =    "$" + arguments?.getString("expenseText") + ".0"
        }
        else if (requireArguments().containsKey("incomeText")) {
            binding.tvAmountIncome.text =  "$" + arguments?.getString("incomeText") + ".0"
        }



        binding.AddIncomeCard.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_addIncomeFragment)
        }

        binding.tvAddExpense.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_addExpenseFragment)
        }



        return binding.root




    }

}