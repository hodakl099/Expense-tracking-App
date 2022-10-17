package com.example.trackingapp.ui.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.trackingapp.databinding.FragmentDetailTransactionBinding
import com.example.trackingapp.ui.utility.formatCurrency
import com.example.trackingapp.ui.viewmodel.TransactionViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailTransactionFragment : Fragment() {



    private lateinit var binding: FragmentDetailTransactionBinding
    private val args: DetailTransactionFragmentArgs by navArgs()
    val transactionViewModel: TransactionViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val detailTransactionBinding = FragmentDetailTransactionBinding.inflate(layoutInflater)
        binding = detailTransactionBinding

        getData()

        return binding.root
    }


    // to get the current transaction data.
    private fun getData() = with(binding) {

        val transactionItem = transactionViewModel.getTransactionById(args.transaction.id)
         transactionItem.observe(viewLifecycleOwner) {
             tvDetailsTitle.text = it.title
             tvTypeDetail.text = it.transactionType
             tvDetailsAmount.text = formatCurrency(it.amount)
             tvDetailsCategory.text = getString(it.category.categoryDescription)
             tvDetailsPaymentMethod.text = it.payment
             tvDetailsDate.text = it.date
             tvDetailsNotes.text = it.note


             val editFabAction =
                 DetailTransactionFragmentDirections.actionDetailTransactionFragmentToEditExpenseFragment(
                     it
                 )
             fabAddTransaction.setOnClickListener {
                 findNavController().navigate(editFabAction)
             }
         }


    }

}