package com.example.trackingapp.ui.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.trackingapp.R
import com.example.trackingapp.databinding.FragmentAddExpenseBinding
import com.example.trackingapp.ui.data.entity.Transaction
import com.example.trackingapp.ui.utility.Constants
import com.example.trackingapp.ui.utils.TransactionCategory
import com.example.trackingapp.ui.viewmodel.TransactionViewModel
import java.util.*


class AddExpenseFragment : Fragment() {


    private lateinit var binding: FragmentAddExpenseBinding

    private lateinit var transactionViewModel: TransactionViewModel


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        val bindingFragment = FragmentAddExpenseBinding.inflate(layoutInflater, container, false)

        transactionViewModel =
            ViewModelProvider(requireActivity())[TransactionViewModel::class.java]

        binding = bindingFragment


        binding.transactionInputFields.etTransactionCategory.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.dropdown_item,
                Constants(requireContext()).TRANSACTION_CATEGORTY
            )

        )

        binding.transactionInputFields.etPaymentMethod.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.dropdown_item,
                Constants(requireContext()).PAYMENT_METHOD
            )
        )

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        //when the user navigates the Calendar will be prompted and the user will choose,
        //the data of the transaction.
            val dpd = DatePickerDialog(requireContext(), { _, mYear, mMonth, mDay ->
                binding.transactionInputFields.let {
                    it.etTransactionDate.setText("$mDay/${mMonth + 1}/$mYear")
                    it.etTransactionDate.apply {
                        isClickable = true
                        isFocusable = true
                        isFocusableInTouchMode
                    }
                }
            }, year, month, day)
            dpd.show()
        //when the user clicks on the text the calendar will be showed.
        binding.transactionInputFields.let {
            it.etTransactionDate.setOnClickListener {
                dpd.show()
            }
        }

        binding.btnAdd.setOnClickListener {

            if (fieldsNullOrEmpty()) {
                Toast.makeText(requireContext(), "All fields required to be filled", Toast.LENGTH_LONG)
                    .show()
            } else {
                if (binding.transactionInputFields.radioExpense.isChecked) {

                    val transactionTitle = binding.transactionInputFields.let {
                        it.etTransactionTitle.text.toString()
                    }

                    val transactionType = binding.transactionInputFields.radioExpense.text.toString()

                    val expenseAmount = binding.transactionInputFields.let {
                        it.etTransactionAmount.text.toString().toDouble()
                    }
                    val transactionCategory = when (binding.transactionInputFields.etTransactionCategory.text.toString()) {
                        getString(R.string.bills) -> TransactionCategory.Bills
                        getString(R.string.food) -> TransactionCategory.Food
                        getString(R.string.education) -> TransactionCategory.Education
                        getString(R.string.entertainment) -> TransactionCategory.Entertainment
                        getString(R.string.housing) -> TransactionCategory.Housing
                        getString(R.string.health) -> TransactionCategory.Health
                        getString(R.string.travel)-> TransactionCategory.Travel
                        getString(R.string.transportation)-> TransactionCategory.Transportation
                        getString(R.string.shopping)-> TransactionCategory.Shopping
                        getString(R.string.salary)-> TransactionCategory.Salary
                        getString(R.string.investments)-> TransactionCategory.Investments
                        getString(R.string.other)-> TransactionCategory.Other
                        else -> TransactionCategory.Other
                    }

                    val transactionDate = binding.transactionInputFields.let {
                        it.etTransactionDate.text.toString()
                    }
                    val transactionPayment = binding.transactionInputFields.etPaymentMethod.text.toString()
                    val transactionNote = binding.transactionInputFields.let {
                        it.etTransactionNotes.text.toString()
                    }



                    val expenseTransaction = Transaction(0,transactionTitle,expenseAmount,transactionCategory,transactionType,transactionDate,transactionPayment,transactionNote)

                    transactionViewModel.addTransaction(expenseTransaction)

                    findNavController().navigate(
                        R.id.action_addExpenseFragment_to_homeFragment,
                    )
                }
                if (binding.transactionInputFields.radioIncome.isChecked) {
                    val transactionTitle = binding.transactionInputFields.let {
                        it.etTransactionTitle.text.toString()
                    }

                    val transactionType = binding.transactionInputFields.radioIncome.text.toString()

                    val incomeAmount = binding.transactionInputFields.let {
                        it.etTransactionAmount.text.toString().toDouble()
                    }
                    val transactionCategory = when (binding.transactionInputFields.etTransactionCategory.text.toString()) {
                        getString(R.string.bills) -> TransactionCategory.Bills
                        getString(R.string.food) -> TransactionCategory.Food
                        getString(R.string.education) -> TransactionCategory.Education
                        getString(R.string.entertainment) -> TransactionCategory.Entertainment
                        getString(R.string.housing) -> TransactionCategory.Housing
                        getString(R.string.health) -> TransactionCategory.Health
                        getString(R.string.travel)-> TransactionCategory.Travel
                        getString(R.string.transportation)-> TransactionCategory.Transportation
                        getString(R.string.shopping)-> TransactionCategory.Shopping
                        getString(R.string.salary)-> TransactionCategory.Salary
                        getString(R.string.investments)-> TransactionCategory.Investments
                        getString(R.string.other)-> TransactionCategory.Other
                        else -> TransactionCategory.Other
                    }

                    val transactionDate = binding.transactionInputFields.let {
                        it.etTransactionDate.text.toString()
                    }
                    val transactionPayment = binding.transactionInputFields.etPaymentMethod.text.toString()
                    val transactionNote = binding.transactionInputFields.let {
                        it.etTransactionNotes.text.toString()
                    }
                    val incomeTransaction = Transaction(0, transactionTitle,incomeAmount, transactionCategory,transactionType,transactionDate,transactionPayment,transactionNote)

                    transactionViewModel.addTransaction(incomeTransaction)

                    findNavController().navigate(
                        R.id.action_addExpenseFragment_to_homeFragment,
                    )
                }
            }
        }

        return binding.root
    }


    // function to check if fields are empty or null.
    private fun fieldsNullOrEmpty() : Boolean {
        binding.transactionInputFields.let {
            it.etTransactionAmount.text.isNullOrEmpty()
            it.etTransactionCategory.text.isNullOrEmpty()
            it.etTransactionDate.text.isNullOrEmpty()
            it.etPaymentMethod.text.isNullOrEmpty()
            it.etTransactionTitle.text.isNullOrEmpty()
        }
        return true
    }
}


