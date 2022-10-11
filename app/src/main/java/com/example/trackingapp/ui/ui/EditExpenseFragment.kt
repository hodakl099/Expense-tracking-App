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
import androidx.navigation.fragment.navArgs
import com.example.trackingapp.R
import com.example.trackingapp.databinding.FragmentEditExpenseBinding
import com.example.trackingapp.ui.data.entity.Transaction
import com.example.trackingapp.ui.utility.Constants
import com.example.trackingapp.ui.utils.TransactionCategory
import com.example.trackingapp.ui.viewmodel.TransactionViewModel
import com.google.android.material.snackbar.Snackbar
import java.lang.Double.parseDouble
import java.text.SimpleDateFormat
import java.util.*


class EditExpenseFragment : Fragment() {

    private lateinit var binding: FragmentEditExpenseBinding
    private lateinit var transactionViewModel: TransactionViewModel

    private val args: EditExpenseFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val bindingFragment = FragmentEditExpenseBinding.inflate(layoutInflater, container, false)

        transactionViewModel =
            ViewModelProvider(requireActivity())[TransactionViewModel::class.java]
        binding = bindingFragment

        getData()
        binding.btnEdit.setOnClickListener{
            validateFields()
            Toast.makeText(requireContext(), "All fields required to be filled", Toast.LENGTH_LONG)
                .show()
        }

        return binding.root
    }

    private fun getData() {

        with(binding.transactionInputFields) {
            etTransactionTitle.setText(args.transaction.title)
            etTransactionAmount.setText(args.transaction.amount.toString())
            if (args.transaction.transactionType == "Expense") {
                radioExpense.isChecked = true
            }
            else  {
                radioIncome.isChecked = true
            }

            etTransactionDate.apply {
                setText(args.transaction.date)
                isFocusable = true
                isClickable = true
                isFocusableInTouchMode = false
                val myCalendar = Calendar.getInstance()
                val sdf = SimpleDateFormat(context.getString(R.string.date_format), Locale.ENGLISH)
                val datePickerOnDateSetListener =
                    DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                        myCalendar.set(Calendar.YEAR, year)
                        myCalendar.set(Calendar.MONTH, monthOfYear)
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        setText(sdf.format(myCalendar.time))
                    }

                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    datePickerOnDateSetListener,
                    myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)
                )

                datePickerDialog.datePicker.maxDate = Date().time
                setOnClickListener {
                    datePickerDialog.show()
                }
            }


            etTransactionNotes.setText(args.transaction.note)

            etTransactionCategory.apply {
                setText(getString(args.transaction.category.categoryDescription))
                setAdapter(
                    ArrayAdapter(
                        requireContext(),
                        R.layout.dropdown_item,
                        Constants(requireContext()).TRANSACTION_CATEGORTY
                    )
                )
            }
            etPaymentMethod.apply {
                setText(args.transaction.payment)
                setAdapter(
                    ArrayAdapter(
                        requireContext(),
                        R.layout.dropdown_item,
                        Constants(requireContext()).PAYMENT_METHOD
                    )
                )
            }
        }
    }

   private fun getCurrentTransactionData() : Transaction  {
       binding.transactionInputFields.let{
           val title = it.etTransactionTitle.text.toString()
           val amount = parseDouble(it.etTransactionAmount.toString())
           val date = it.etTransactionDate.text.toString()
           val transactionCategory = when(it.etTransactionCategory.text.toString()) {
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
           val transactionPayment = it.etPaymentMethod.toString()
           val transactionType = if(it.radioExpense.isChecked){
               getString(R.string.income)
           } else {
               getString(R.string.expense)
           }
           val transactionNote = it.etTransactionNotes.text.toString()

           return Transaction(
               id = 0,
               title = title,
               transactionType = transactionType,
               amount = amount,
               category = transactionCategory,
               payment = transactionPayment,
               date = date,
               note = transactionNote,

           )
       }
   }


    private fun validateFields() {
        if (binding.transactionInputFields.let {
                it.etTransactionAmount.text.isNullOrEmpty()
                it.etTransactionCategory.text.isNullOrEmpty()
                it.etTransactionDate.text.isNullOrEmpty()
                it.etPaymentMethod.text.isNullOrEmpty()
                it.etTransactionTitle.text.isNullOrEmpty()
            } ) {
            Toast.makeText(requireContext(), "All fields required to be filled", Toast.LENGTH_LONG)
                .show()
        } else {
            if (binding.transactionInputFields.let{
                    it.radioExpense.isChecked
                }) {


                val transactionId = args.transaction.id

                val transactionTitle = binding.transactionInputFields.let {
                    it.etTransactionTitle.text.toString()
                }

                val transactionType = binding.transactionInputFields.let {
                    it.radioExpense.text.toString()
                }

                val expenseAmount = binding.transactionInputFields.let {
                    it.etTransactionAmount.text.toString().toDouble()
                }
                val transactionCategory = when (binding.transactionInputFields.let {
                    it.etTransactionCategory.text.toString()
                }) {
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
                val transactionPayment = binding.transactionInputFields.let {
                    it.etPaymentMethod.text.toString()
                }
                val transactionNote = binding.transactionInputFields.let {
                    it.etTransactionNotes.text.toString()
                }



                val expenseTransaction = Transaction(transactionId,transactionTitle,expenseAmount,transactionCategory,transactionType,transactionDate,transactionPayment,transactionNote,222)

                transactionViewModel.updateTransaction(expenseTransaction)
                Snackbar.make(binding.root,"Transaction Saved", Snackbar.LENGTH_SHORT)
                    .show()
                findNavController().navigateUp()
            }
            if (binding.transactionInputFields.let{
                    it.radioIncome.isChecked
                }) {

                val transactionId = args.transaction.id

                val transactionTitle = binding.transactionInputFields.let {
                    it.etTransactionTitle.text.toString()
                }

                val transactionType = binding.transactionInputFields.let {
                    it.radioIncome.text.toString()
                }

                val incomeAmount = binding.transactionInputFields.let {
                    it.etTransactionAmount.text.toString().toDouble()
                }
                val transactionCategory = when (binding.transactionInputFields.let {
                    it.etTransactionCategory.text.toString()
                }) {
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
                val transactionPayment = binding.transactionInputFields.let {
                    it.etPaymentMethod.text.toString()
                }
                val transactionNote = binding.transactionInputFields.let {
                    it.etTransactionNotes.text.toString()
                }
                val incomeTransaction = Transaction(transactionId, transactionTitle,incomeAmount, transactionCategory,transactionType,transactionDate,transactionPayment,transactionNote,122)

                transactionViewModel.updateTransaction(incomeTransaction)
                Snackbar.make(binding.root,"Transaction Saved", Snackbar.LENGTH_SHORT)
                    .show()
                findNavController().navigateUp()

            }
        }
    }
}

