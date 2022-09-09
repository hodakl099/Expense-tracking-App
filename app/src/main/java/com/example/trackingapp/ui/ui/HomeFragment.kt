package com.example.trackingapp.ui.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trackingapp.R
import com.example.trackingapp.databinding.FragmentHomeBinding
import java.text.DecimalFormat
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
           binding.tvAmountIncome.text = "$00.0"
        }
       else if(requireArguments().containsKey("expenseText")) {
            formatArgumentCurrency("expenseText", binding.tvAmountExpense)
        }
       else if (requireArguments().containsKey("incomeText")) {
            formatArgumentCurrency("incomeText", binding.tvAmountIncome)
        }



        binding.AddIncomeCard.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_addIncomeFragment)
        }

        binding.tvAddExpense.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_addExpenseFragment)
        }



        return binding.root

    }

    //function to format the currency.
    private fun formatArgumentCurrency(argument : String, textView: TextView) {

        val valueText = requireArguments().get(argument).toString()
        val dec = DecimalFormat("#,###.##")
        val number = java.lang.Double.valueOf(valueText)
        val value = dec.format(number)
        val currency = Currency.getInstance("USD")
        val symbol = currency.symbol
        textView.text = String.format("$symbol$value","%.2f" )

    }

}