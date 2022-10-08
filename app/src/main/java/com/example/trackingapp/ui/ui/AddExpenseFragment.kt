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


        val categories = resources.getStringArray(R.array.categories_array)
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_item,
            categories
        )
        binding.transactionInputFields.let{
            it.etTransactionCategory.setAdapter(arrayAdapter)
        }
        val paymentMethods = resources.getStringArray(R.array.payment_methods)
        val arrayPaymentAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_item,
            paymentMethods
        )
        binding.transactionInputFields.let {
            it.etPaymentMethod.setAdapter(arrayPaymentAdapter)
        }

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        //when the user navigates the Calendar will be prompted and the user will choose,
        //the data of the transaction.
            val dpd = DatePickerDialog(requireContext(), { view, mYear, mMonth, mDay ->
                binding.transactionInputFields.let {
                    it.etTransactionDate.setText("$mDay/${mMonth + 1}/$mYear")
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

            if (binding.transactionInputFields.let {
                   it.etTransactionAmount.text.isNullOrEmpty()
                } ) {
                Toast.makeText(requireContext(), "Please enter a valid amount", Toast.LENGTH_LONG)
                    .show()
            } else {
                if (binding.transactionInputFields.let{
                    it.radioExpense.isChecked
                    }) {
                    val expenseAmount = binding.transactionInputFields.let {
                        it.etTransactionAmount.text.toString().toDouble()
                    }

                    val transactionType = binding.transactionInputFields.let {
                        it.radioExpense.text.toString()
                    }

                    val expenseTransaction = Transaction(0, "Test",expenseAmount, "",transactionType,"","","",222)

                    transactionViewModel.addTransaction(expenseTransaction)

                    findNavController().navigate(
                        R.id.action_addExpenseFragment_to_homeFragment,
                    )
                }
                if (binding.transactionInputFields.let{
                        it.radioIncome.isChecked
                    }) {
                    val incomeAmount = binding.transactionInputFields.let {
                        it.etTransactionAmount.text.toString().toDouble()
                    }

                    val transactionType = binding.transactionInputFields.let {
                        it.radioIncome.text.toString()
                    }

                    val incomeTransaction = Transaction(0, "Test",incomeAmount, "",transactionType,"","","",122)

                    transactionViewModel.addTransaction(incomeTransaction)

                    findNavController().navigate(
                        R.id.action_addExpenseFragment_to_homeFragment,
                    )
                }
            }
        }

        return binding.root
    }
}


