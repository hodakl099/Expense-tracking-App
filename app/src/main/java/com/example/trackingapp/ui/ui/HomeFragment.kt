package com.example.trackingapp.ui.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.*
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

        // Inflate the layout for this fragment
        val bindingHomeFragment = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding = bindingHomeFragment

//        binding.homeToolBar.inflateMenu(R.menu.menu_top_bar)

        transactionViewModel = ViewModelProvider(requireActivity())[TransactionViewModel::class.java]




            transactionViewModel.getExpense.observe(viewLifecycleOwner) { column ->

                //format expense
                val expenseAmount = column.sumOf { it.Expense }
                val decExpense = DecimalFormat("#,###.##")
                val numberExpense = java.lang.Double.valueOf(expenseAmount)
                val valueExpense = decExpense.format(numberExpense)
                val currency = Currency.getInstance("USD")
                val symbol= currency.symbol
                val formatExpense = String.format("$symbol$valueExpense","%.2f" )
                binding.tvAmountExpense.text = formatExpense

                //format Income
                val incomeAmount = column.sumOf { it.Income }
                val decIncome = DecimalFormat("#,###.##")
                val numberIncome = java.lang.Double.valueOf(incomeAmount)
                val valueIncome = decIncome.format(numberIncome)
                val formatIncome = String.format("$symbol$valueIncome","%.2f" )
                binding.tvAmountIncome.text = formatIncome


                //formatBalanceAmount
                val balanceAmount = incomeAmount - expenseAmount
                val decBalance = DecimalFormat("#,###.##")
                val numberBalance = java.lang.Double.valueOf(balanceAmount)
                val valueBalance = decBalance.format(numberBalance)
                val formatBalance = String.format("$symbol$valueBalance","%.2f" )
                binding.tvAmountBalance.text = formatBalance

                loadPieChartData(
                  expenseAmount = expenseAmount.toFloat(),
                  incomeAmount =   incomeAmount.toFloat()
                )


        }


        bindingHomeFragment.AddIncomeCard.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_addExpenseFragment)
        }

        bindingHomeFragment.tvAddExpense.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_addExpenseFragment)
        }

        bindingHomeFragment.transactionCard.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_transactionFragment)
        }

        setupPieChart()


        return bindingHomeFragment.root

    }

    private fun loadPieChartData(expenseAmount : Float = 0.00f,incomeAmount : Float = 0.00f) {
        val entries: ArrayList<PieEntry> = ArrayList()

        entries.add(PieEntry(incomeAmount,"Income"))

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