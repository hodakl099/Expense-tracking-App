package com.example.trackingapp.ui.ui.menufragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.trackingapp.databinding.FragmentSettingBinding
import com.example.trackingapp.ui.utility.formatCurrency
import com.example.trackingapp.ui.viewmodel.TransactionViewModel
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


class SettingFragment : Fragment() {


    private lateinit var binding: FragmentSettingBinding

    private lateinit var transactionViewModel: TransactionViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentSettings = FragmentSettingBinding.inflate(layoutInflater,container,false)

        transactionViewModel = ViewModelProvider(requireActivity())[TransactionViewModel::class.java]

        binding = fragmentSettings




        getPreference()
        setupWarning()
        binding.btnSave.setOnClickListener { btn ->

            if (binding.etExpenseLimit.text!!.isNotEmpty()) {
                updatePreference()
                Toast.makeText(requireContext(),"limit is saved", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(requireContext(),"limit is invalid", Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }


    private fun updatePreference()  {
        lifecycleScope.launchWhenStarted {
            val expenseLimit = binding.etExpenseLimit.text.toString().toInt()
            transactionViewModel.setLimit("limit", expenseLimit)

        }
    }

    private fun getPreference() {

        lifecycleScope.launch{
          val expenseLimit =   transactionViewModel.getLimit("limit")
            if (expenseLimit != null) {
                binding.etExpenseLimit.setText(expenseLimit.toString())
            }

        }

    }


     @SuppressLint("SetTextI18n")
     fun setupWarning() {
        lifecycleScope.launchWhenStarted {
            val expenseSpendingLimit = transactionViewModel.getLimit("limit")

            transactionViewModel.getTransactionExpense.observe(viewLifecycleOwner) {

                val expenseAmount = it.sumOf { it.amount }

                val spendingOnLimit = if (expenseSpendingLimit == null || expenseSpendingLimit == 0) {
                    binding.spendingProgress.tvSpendingLimit.text =
                        "limit not set"
                    0.00
                } else {
                    binding.spendingProgress.tvSpendingLimit.text =
                        formatCurrency(expenseSpendingLimit.toDouble())
                    expenseAmount / expenseSpendingLimit
                }


                val spendingPercentage = if (spendingOnLimit > 0) {
                    (spendingOnLimit * 100).roundToInt()
                } else {
                    0
                }

                binding.spendingProgress.tvProgressPercentage.text = "$spendingPercentage%"

                val progress = if (spendingOnLimit > 0) {
                    (spendingOnLimit * 10).roundToInt()
                } else {
                    0
                }
                binding.spendingProgress.progressBarSpending.progress = progress

                transactionViewModel.isWarningIsGone.observe(viewLifecycleOwner) { warning ->

                    if (spendingPercentage >= 70 && !warning) {

                        binding.warningView.root.visibility = View.VISIBLE

                    } else {
                        binding.warningView.root.visibility = View.GONE
                    }

                }

                binding.warningView.closeIcon.setOnClickListener {
                    binding.warningView.root.visibility = View.GONE
                    transactionViewModel.isWarningIsGone.postValue(true)
                }
            }

        }

    }


}