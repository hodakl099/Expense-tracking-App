package com.example.trackingapp.ui.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trackingapp.R
import com.example.trackingapp.databinding.FragmentHomeBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import java.text.DecimalFormat
import java.util.*


class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding



    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

//        binding.homeMainPiechart
        setupPieChart()
        loadPieChartData()

        if (arguments == null) {
           binding.tvAmountExpense.text = "$00.0"
           binding.tvAmountIncome.text = "$00.0"
        }
       else if(requireArguments().containsKey("expenseText")) {
            formatArgumentCurrency("expenseText", binding.tvAmountExpense)
        }
       else if (requireArguments().containsKey("incomeText")) {
            formatArgumentCurrency("incomeText", binding.tvAmountIncome)
        }

        binding.AddIncomeCard.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_addIncomeFragment)
        }

        binding.tvAddExpense.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_addExpenseFragment)
        }


        return binding.root

    }

    //function to format the currency.
    private fun formatArgumentCurrency(argument : String, textView: TextView) {

        val valueText = requireArguments().get(argument).toString()
        val dec = DecimalFormat("#,###.##")
        val number = java.lang.Double.valueOf(valueText)
        val value = dec.format(number)
        val currency = Currency.getInstance("USD")
        val symbol = currency.symbol
        textView.text = String.format("$symbol$value","%.2f" )


    }
    private fun loadPieChartData() {
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(02f, "food and Dining"))
        entries.add(PieEntry(015f, "Medical"))
        entries.add(PieEntry(010f, "Entertainment"))
        entries.add(PieEntry(025f, "Electricity"))
        entries.add(PieEntry(03f, "Housing"))

        val colors  =  ArrayList<Int>()
        for(color : Int in ColorTemplate.MATERIAL_COLORS) {
            colors.add(color)
        }

        for(color : Int in ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color)
        }

        val dataSet = PieDataSet(entries,"Expense")
        dataSet.color = colors.size

        val data = PieData(dataSet)
        data.setDrawValues(true)
        data.setValueFormatter(PercentFormatter(binding.homeMainPiechart))

        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        binding.homeMainPiechart.data =  data
        binding.homeMainPiechart.invalidate();

        binding.homeMainPiechart.animateY(1400, Easing.EaseInOutQuad);

    }
    private fun setupPieChart() {
        binding.homeMainPiechart.isDrawHoleEnabled = true
        binding.homeMainPiechart.setUsePercentValues(true)
        binding.homeMainPiechart.setEntryLabelTextSize(12F)
        binding.homeMainPiechart.setEntryLabelColor(Color.BLACK)
        binding.homeMainPiechart.centerText = "Spending by Category"
        binding.homeMainPiechart.setCenterTextSize(24F)
        binding.homeMainPiechart.description.isEnabled = false
        val l=  binding.homeMainPiechart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.isEnabled = true
    }


}