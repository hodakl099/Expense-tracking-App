package com.example.trackingapp.ui.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.trackingapp.R
import com.example.trackingapp.databinding.FragmentHomeBinding
import com.example.trackingapp.ui.viewmodel.TransactionViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import java.text.DecimalFormat
import java.util.*


class HomeFragment : androidx.fragment.app.Fragment() {



    private lateinit var binding : FragmentHomeBinding

    private lateinit var transactionViewModel: TransactionViewModel




    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //this is
        // Inflate the layout for this fragment
        val bindingHomeFragment = FragmentHomeBinding.inflate(layoutInflater, container, false)

        transactionViewModel = ViewModelProvider(requireActivity())[TransactionViewModel::class.java]



        binding = bindingHomeFragment


            transactionViewModel.getExpense.observe(viewLifecycleOwner) {
                val expenseAmount = it.sumOf { it.Expense }
//                = "$%.2f".format(expenseAmount)
//                val expenseValue =  binding.tvAmountExpense.text.toString()
                val dec = DecimalFormat("#,###.##")
                val number = java.lang.Double.valueOf(expenseAmount)
                val value = dec.format(number)
                val currency = Currency.getInstance("USD")
                val symbol = currency.symbol
                val format = String.format("$symbol$value","%.2f" )
                binding.tvAmountExpense.text = format

                loadPieChartData(
                    expenseAmount.toFloat()
                )


        }


        bindingHomeFragment.AddIncomeCard.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_addIncomeFragment)
        }

        bindingHomeFragment.tvAddExpense.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_addExpenseFragment)
        }

        setupPieChart()



        return bindingHomeFragment.root

    }

    private fun loadPieChartData(expenseAmount : Float = 0.00f) {
        val entries: ArrayList<PieEntry> = ArrayList()
        if(arguments == null)
            entries.add(PieEntry(0.00f, "Income"))
        else
            entries.add(PieEntry(binding.tvAmountExpense.text.toString().toFloat(),"Income"))

        entries.add(PieEntry(expenseAmount,"Expense"))

        val colors: ArrayList<Int> = ArrayList()
        for (color in ColorTemplate.MATERIAL_COLORS) {
            colors.add(color)
        }

        val dataSet = PieDataSet(entries, "")
        dataSet.colors = colors
        val data = PieData(dataSet)
        data.setDrawValues(true)
        data.setValueFormatter(PercentFormatter(binding.homeMainPiechart))
        data.setValueTextSize(12f)
        data.setValueTextColor(Color.BLACK)
        binding.homeMainPiechart.data = data
        binding.homeMainPiechart.invalidate()
        binding.homeMainPiechart.animateY(1400, Easing.EaseInOutQuad)
    }


    private fun setupPieChart() {

        binding.homeMainPiechart.isDrawHoleEnabled = false
        binding.homeMainPiechart.setUsePercentValues(true)
        binding.homeMainPiechart.setEntryLabelTextSize(12f)
        binding.homeMainPiechart.setEntryLabelColor(Color.BLACK)
        binding.homeMainPiechart.setCenterTextSize(24f)
        binding.homeMainPiechart.description.isEnabled = false
        val legend = binding.homeMainPiechart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.setDrawInside(false)
        legend.isEnabled = true

    }


}