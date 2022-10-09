package com.example.trackingapp.ui.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.trackingapp.databinding.FragmentDetailTransactionBinding
import com.example.trackingapp.ui.viewmodel.TransactionViewModel
import kotlinx.coroutines.launch


class DetailTransactionFragment : Fragment() {



    private lateinit var binding: FragmentDetailTransactionBinding
    private val args: DetailTransactionFragmentArgs by navArgs()
    private lateinit var transactionViewModel: TransactionViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val detailTransactionBinding = FragmentDetailTransactionBinding.inflate(layoutInflater)
        binding = detailTransactionBinding

        transactionViewModel = ViewModelProvider(requireActivity())[TransactionViewModel::class.java]
        getData()

        return binding.root
    }

    private fun getData() = with(binding) {

        val transactionItem = transactionViewModel.getTransactionById(args.transaction.id)
         transactionItem.observe(viewLifecycleOwner) {
             tvDetailsTitle.text = it.title
             tvTypeDetail.text = it.transactionType
             tvDetailsAmount.text = it.amount.toString()
//             tvDetailsCategory.text = it.category.categoryDescription
             tvDetailsPaymentMethod.text = it.payment
             tvDetailsDate.text = it.date
             tvDetailsNotes.text = it.note
         }


    }

}