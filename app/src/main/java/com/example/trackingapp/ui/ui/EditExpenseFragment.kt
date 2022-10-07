package com.example.trackingapp.ui.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.trackingapp.R
import com.example.trackingapp.databinding.FragmentEditExpenseBinding
import com.example.trackingapp.ui.viewmodel.TransactionViewModel
import java.text.NumberFormat
import java.util.*


class EditExpenseFragment : Fragment() {


    private lateinit var binding: FragmentEditExpenseBinding

    private lateinit var transactionViewModel: TransactionViewModel

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


        val categories = resources.getStringArray(R.array.categories_array)
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_item,
            categories
        )
        binding.tvAutoCompleteCategory.setAdapter(arrayAdapter)

        val paymentMethods = resources.getStringArray(R.array.payment_methods)
        val arrayPaymentAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_item,
            paymentMethods
        )
        binding.tvAutoCompletePaymentText.setAdapter(arrayPaymentAdapter)

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        binding.tvDateText.setOnClickListener {
            val dpd = DatePickerDialog(requireContext(), { view, mYear, mMonth, mDay ->

                binding.tvDateText.text = "$mDay/${mMonth + 1}/$mYear"

            }, year, month, day)
            dpd.show()
        }

        val text = requireArguments().getString("transactionAmount")
        val value = text!!.replace("$", "")
        binding.inputTextAmount.setText(value)


        return binding.root
    }



}


