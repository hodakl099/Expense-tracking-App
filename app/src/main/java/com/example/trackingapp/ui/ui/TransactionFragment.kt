package com.example.trackingapp.ui.ui

import android.annotation.SuppressLint

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trackingapp.R
import com.example.trackingapp.databinding.FragmentTransactionBinding
import com.example.trackingapp.ui.adapters.TransactionAdapter
import com.example.trackingapp.ui.adapters.TransactionClickListener
import com.example.trackingapp.ui.data.TransactionColor
import com.example.trackingapp.ui.data.entity.Transaction
import com.example.trackingapp.ui.viewmodel.TransactionViewModel
import java.text.DecimalFormat
import java.util.*


class TransactionFragment : Fragment(), TransactionClickListener {


    private lateinit var binding: FragmentTransactionBinding

    private lateinit var recyclerView : RecyclerView

    private lateinit var adapter: TransactionAdapter

    private lateinit var transactionViewModel: TransactionViewModel

    private lateinit var listener : TransactionClickListener




    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val bindingFragmentTransaction = FragmentTransactionBinding.inflate(layoutInflater, container,false)
        binding = bindingFragmentTransaction



        transactionViewModel = ViewModelProvider(requireActivity())[TransactionViewModel::class.java]


        listener = this

        val transactionAdapter = TransactionAdapter()

        recyclerView = binding.rvTransaction

        recyclerView.adapter = transactionAdapter



        transactionViewModel.getAllTransaction.observe(viewLifecycleOwner){transactions->
                 adapter.differ.submitList(transactions)

        }

        setUpRecyclerView()
        return binding.root
    }

    override fun onTransactionClickListener(view: View, transaction: Transaction) {
        TODO("Not yet implemented")
    }

    private fun setUpRecyclerView() = lifecycleScope.launchWhenCreated {
        adapter = TransactionAdapter()
        binding.rvTransaction.adapter = adapter
        binding.rvTransaction.layoutManager = LinearLayoutManager(activity)
        adapter.setOnItemClickListener {
            Toast.makeText(requireContext(), "item${it.amount}",Toast.LENGTH_LONG).show()
        }
    }



}