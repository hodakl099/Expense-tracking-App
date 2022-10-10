package com.example.trackingapp.ui.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.trackingapp.R
import com.example.trackingapp.databinding.FragmentHomeBinding
import com.example.trackingapp.ui.utility.formatCurrency
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


class HomeFragment : androidx.fragment.app.Fragment(),MenuProvider {

    private lateinit var binding : FragmentHomeBinding

    private lateinit var transactionViewModel: TransactionViewModel


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        // Inflate the layout for this fragment
        val bindingHomeFragment = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding = bindingHomeFragment

        transactionViewModel = ViewModelProvider(requireActivity())[TransactionViewModel::class.java]



     transactionViewModel.getTransactionExpense.observe(viewLifecycleOwner) { expense ->

                //format expense
                val expenseAmount =expense.sumOf { it.amount }
                val formattedExpense = formatCurrency(expenseAmount)
                binding.tvAmountExpense.text = formattedExpense

     }

        transactionViewModel.getTransactionIncome.observe(viewLifecycleOwner) { income ->

            val incomeAmount = income.sumOf {
                it.amount
            }
            val formattedIncome = formatCurrency(incomeAmount)
            binding.tvAmountIncome.text = formattedIncome
        }


        transactionViewModel.getAllTransaction.observe(viewLifecycleOwner) { transaction ->

            val (totalIncome, totalExpense ) =
                transaction.partition {
                    it.transactionType == "Income"
                }

            val incomeAmount = totalIncome.sumOf { it.amount }
            val expenseAmount = totalExpense.sumOf { it.amount }
            val balanceAmount = incomeAmount - expenseAmount

            //format Balance
            val formattedBalance = formatCurrency(balanceAmount)
            binding.tvAmountBalance.text = formattedBalance


            // if the value is less than $00.0 then set the color of the text to red.
            if (balanceAmount < 0.00) {
               binding.tvAmountBalance.setTextColor(
               ContextCompat.getColor(
                   binding.tvAmountBalance.context,
                   R.color.holo_red_light)
               )
            }

            loadPieChartData(expenseAmount = expenseAmount.toFloat(), incomeAmount = incomeAmount.toFloat())

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

        loadPieChartData()
        setupPieChart()



        return bindingHomeFragment.root

    }

    private fun loadPieChartData(expenseAmount : Float = 00.0f,incomeAmount : Float = 0.00f) {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.addMenuProvider(this,viewLifecycleOwner)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.home_main_fragmnet,menu)
    }

    override fun onMenuItemSelected(item: MenuItem): Boolean {
        val actionToStatisticsFragment =
            HomeFragmentDirections.actionHomeFragmentToStatisticsFragment()

        val actionToCalendarFragment =
            HomeFragmentDirections.actionHomeFragmentToCalendarFragment()

        val actionToSettingsFragment =
            HomeFragmentDirections.actionHomeFragmentToSettingFragment()
        return when (item.itemId) {
            R.id.statisticsFragment -> {
                requireView().findNavController().navigate(actionToStatisticsFragment)
                true
            }
            R.id.calendarFragment ->{
               requireView().findNavController().navigate(actionToCalendarFragment)
            true
        }
            R.id.settingsFragment -> {
                requireView().findNavController().navigate(actionToSettingsFragment)
                true
            }
            else -> false
    }


}
}