package com.example.trackingapp.ui.ui.menufragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.trackingapp.databinding.FragmentExpenseStatisticsBinding
import com.example.trackingapp.ui.utils.TransactionCategory
import com.example.trackingapp.ui.viewmodel.TransactionViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.ArrayList


class ExpenseStatisticsFragment : Fragment() {


    private lateinit var binding : FragmentExpenseStatisticsBinding

    private lateinit var transactionViewModel: TransactionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val fragmentExpenseStatistics = FragmentExpenseStatisticsBinding.inflate(layoutInflater,container,false)

        transactionViewModel = ViewModelProvider(requireActivity())[TransactionViewModel::class.java]

        binding = fragmentExpenseStatistics

        setupPieChart()
        loadPieChartData()

        return binding.root
    }


    private fun loadPieChartData() {

        transactionViewModel.getTransactionExpense.observe(viewLifecycleOwner) {expenseTransaction ->
         val expenseAmount =   expenseTransaction.sumOf { it.amount }

            val entries: ArrayList<PieEntry> = ArrayList()
            val expenseChart = binding.expenseChart


            for (category in TransactionCategory.TRANSACTION_CATEGORIES) {


                val categoryList =
                    expenseTransaction.filter { it.category.categoryDescription == category.categoryDescription }
                val totalCategory = categoryList.sumOf { it.amount }
                val percentageValue = (totalCategory / expenseAmount).toFloat()
                if (totalCategory > 0) {
                    entries.add(PieEntry(percentageValue, getString(category.categoryDescription)))
                }
            }


            val colors: ArrayList<Int> = ArrayList()
            for (color in ColorTemplate.MATERIAL_COLORS) {
                colors.add(color)
            }

            val dataSet = PieDataSet(entries, "")
            dataSet.colors = colors
            val data = PieData(dataSet)
            data.setDrawValues(true)
            data.setValueFormatter(PercentFormatter(expenseChart))
            data.setValueTextSize(12f)
            data.setValueTextColor(Color.BLACK)
            expenseChart.data = data
            expenseChart.invalidate()
            expenseChart.animateY(1400, Easing.EaseInOutQuad)
        }

    }

    private fun setupPieChart() {

        binding.expenseChart.isDrawHoleEnabled = false
        binding.expenseChart.setUsePercentValues(true)
        binding.expenseChart.setEntryLabelTextSize(12f)
        binding.expenseChart.centerText = "Expense By Category"
        binding.expenseChart.setEntryLabelColor(Color.WHITE)
        binding.expenseChart.setCenterTextSize(24f)
        binding.expenseChart.description.isEnabled = false
        val legend = binding.expenseChart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.setDrawInside(false)
        legend.isEnabled = true


    }

}