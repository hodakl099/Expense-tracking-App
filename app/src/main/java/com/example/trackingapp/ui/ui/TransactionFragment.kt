package com.example.trackingapp.ui.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trackingapp.databinding.FragmentTransactionBinding
import com.example.trackingapp.ui.adapters.TransactionAdapter
import com.example.trackingapp.ui.data.Transaction


class TransactionFragment : Fragment() {


    private lateinit var binding: FragmentTransactionBinding

    private val list = mutableListOf<Transaction>()

    private lateinit var recyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val bindingFragmentTransaction = FragmentTransactionBinding.inflate(layoutInflater, container,false)
        binding = bindingFragmentTransaction

        list.add(Transaction("$00.0"))
        list.add(Transaction("$00.0"))
        list.add(Transaction("$00.0"))
        list.add(Transaction("$00.0"))
        list.add(Transaction("$00.0"))
        list.add(Transaction("$00.0"))
        list.add(Transaction("$00.0"))
        list.add(Transaction("$00.0"))


        val transactionAdapter = TransactionAdapter(list)

        recyclerView = binding.rvTransaction

        recyclerView.adapter = transactionAdapter



        return binding.root


    }


}