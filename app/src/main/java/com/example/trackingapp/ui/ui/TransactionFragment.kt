package com.example.trackingapp.ui.ui

import android.annotation.SuppressLint
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.trackingapp.R
import com.example.trackingapp.databinding.FragmentTransactionBinding
import com.example.trackingapp.ui.adapters.TransactionAdapter
import com.example.trackingapp.ui.data.Transaction
import com.example.trackingapp.ui.viewmodel.TransactionViewModel
import java.text.DecimalFormat
import java.util.*


class TransactionFragment : Fragment() {


    private lateinit var binding: FragmentTransactionBinding

    private val list = mutableListOf<Transaction>()

    private lateinit var recyclerView : RecyclerView

    private lateinit var transactionViewModel: TransactionViewModel


    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val bindingFragmentTransaction = FragmentTransactionBinding.inflate(layoutInflater, container,false)
        binding = bindingFragmentTransaction


        transactionViewModel = ViewModelProvider(requireActivity())[TransactionViewModel::class.java]



        val transactionAdapter = TransactionAdapter(list,requireContext())

        recyclerView = binding.rvTransaction

        recyclerView.adapter = transactionAdapter

        transactionViewModel.getExpense.observe(viewLifecycleOwner){ data ->
            data.listIterator().forEach { column ->

                if (column.Expense == column.Expense) {
                    //if the its the default value which is 00.0 then return the foreach list iterator.
                    if (column.Expense == 00.0) return@forEach
                    else {
                        //format expense
                        val decExpense = DecimalFormat("#,###.##")
                        val numberExpense = java.lang.Double.valueOf(column.Expense)
                        val valueExpense = decExpense.format(numberExpense)
                        val currency = Currency.getInstance("USD")
                        val symbol = currency.symbol
                        val formattedExpense = String.format("$symbol$valueExpense", "%.2f")
                        addTransaction(formattedExpense)
                    }
                } else {
                    return@observe
                }

            }

        }





        return binding.root


    }
    private fun addTransaction(data : String) {
        list.add(Transaction(data))
    }


}