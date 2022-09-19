package com.example.trackingapp.ui.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.trackingapp.R
import com.example.trackingapp.databinding.FragmentHomeBinding
import com.example.trackingapp.ui.data.AppDatabase
import com.example.trackingapp.ui.data.Category
import com.example.trackingapp.ui.viewmodel.SharedViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.coroutines.*
import java.text.DecimalFormat
import java.util.*


class HomeFragment : Fragment() {



    private lateinit var bindingA : FragmentHomeBinding

    private lateinit var appDatabase: AppDatabase

    private  var category: Category? = null

    private val sharedViewModel: SharedViewModel by activityViewModels()



    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        bindingA = binding

        appDatabase = AppDatabase.getDatabase(requireContext())



        if (arguments == null) {
            binding.tvAmountExpense.text = "$00.0"
            binding.tvAmountIncome.text = "$00.0"
        }
        else if(requireArguments().containsKey("expenseText")) {
           formatArgumentCurrency("expenseText", binding.tvAmountExpense,null)
        }
        else if (requireArguments().containsKey("incomeText")) {
           formatArgumentCurrency("incomeText",null, binding.tvAmountIncome)
        }

        binding.AddIncomeCard.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_addIncomeFragment)
        }

        binding.tvAddExpense.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_addExpenseFragment)
        }

        setupPieChart()
        loadPieChartData()
        displayData()


        return binding.root

    }


//    override fun onStart() {
//        GlobalScope.launch {
//            getData()
//        }
//        super.onStart()
//    }

    override fun onResume() {
        super.onResume()
    }

    //function to format the currency.
    private fun formatArgumentCurrency(argument : String,
                                       textViewExpense: TextView?,
                                       textViewIncome: TextView?){

        val valueText = requireArguments().getString(argument).toString()
        val dec = DecimalFormat("#,###.##")
        val number = java.lang.Double.valueOf(valueText)
        val value = dec.format(number)
        val currency = Currency.getInstance("USD")
        val symbol = currency.symbol

        if (textViewExpense != null) {
            textViewExpense.text = String.format("$symbol$value","%.2f" )
            saveData(textViewExpense.text.toString(),null)
        }
        if (textViewIncome != null) {
            textViewIncome.text = String.format("$symbol$value","%.2f" )
            saveData(null,textViewIncome.text.toString())
        }



    }

    private fun loadPieChartData() {
        val entries: ArrayList<PieEntry> = ArrayList()
        if(arguments == null)
            entries.add(PieEntry(0.00f, "Income"))
        else
            entries.add(PieEntry(requireArguments().getFloat("incomeText"),"Income"))

        if (arguments == null)
            entries.add(PieEntry(0.00f, "Expense"))
        else
            entries.add(PieEntry(requireArguments().getFloat("expenseText"),"Expense"))

        val colors: ArrayList<Int> = ArrayList()
        for (color in ColorTemplate.MATERIAL_COLORS) {
            colors.add(color)
        }

        val dataSet = PieDataSet(entries, "")
        dataSet.colors = colors
        val data = PieData(dataSet)
        data.setDrawValues(true)
        data.setValueFormatter(PercentFormatter(bindingA.homeMainPiechart))
        data.setValueTextSize(12f)
        data.setValueTextColor(Color.BLACK)
        bindingA.homeMainPiechart.data = data
        bindingA.homeMainPiechart.invalidate()
        bindingA.homeMainPiechart.animateY(1400, Easing.EaseInOutQuad)
    }


    private fun setupPieChart() {


        bindingA.homeMainPiechart.isDrawHoleEnabled = false
        bindingA.homeMainPiechart.setUsePercentValues(true)
        bindingA.homeMainPiechart.setEntryLabelTextSize(12f)
        bindingA.homeMainPiechart.setEntryLabelColor(Color.BLACK)
        bindingA.homeMainPiechart.setCenterTextSize(24f)
        bindingA.homeMainPiechart.description.isEnabled = false
        val legend = bindingA.homeMainPiechart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.setDrawInside(false)
        legend.isEnabled = true


    }

    private fun saveData(formatExpenseText: String?,formatIncomeText : String?){



        if (formatExpenseText!!.isNotEmpty()) {
            bindingA.tvAmountExpense.text = formatExpenseText
        }
        if (formatIncomeText!!.isNotEmpty()) {
            bindingA.tvAmountIncome.text = formatIncomeText
        }




          if (formatExpenseText.isNotEmpty() && formatIncomeText.isNotEmpty() ){
            val categories = Category(
                null,
                formatExpenseText,
                formatIncomeText
            )

            GlobalScope.launch(Dispatchers.IO){
                appDatabase.categoryDao().insert(categories)
            }
        }

    }

    private suspend fun getData(category: Category) {

        withContext(Dispatchers.Main){
            bindingA.tvAmountExpense.text = category.Expense
            bindingA.tvAmountIncome.text = category.Income
        }
    }
    private fun displayData() {

        var category : Category
        GlobalScope.launch {
            category = appDatabase.categoryDao().getAll()[id]
            getData(category)
        }
    }



}