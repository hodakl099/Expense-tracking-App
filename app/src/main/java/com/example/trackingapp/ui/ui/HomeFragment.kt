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


    private lateinit var bindingA : FragmentHomeBinding


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        bindingA = binding

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

//        binding.AddIncomeCard.setOnClickListener{
//            findNavController().navigate(R.id.action_homeFragment_to_addIncomeFragment)
//        }
//
//        binding.tvAddExpense.setOnClickListener{
//            findNavController().navigate(R.id.action_homeFragment_to_addExpenseFragment)
//        }
//        <androidx.cardview.widget.CardView
//        android:id="@+id/AddIncomeCard"
//        android:layout_width="120dp"
//        android:layout_height="120dp"
//        android:layout_marginTop="4dp"
//        android:backgroundTint="@color/background"
//        app:cardCornerRadius="20dp"
//        app:layout_constraintEnd_toEndOf="parent"
//        app:layout_constraintHorizontal_bias="0.5"
//        app:layout_constraintStart_toEndOf="@+id/transactionCard"
//        app:layout_constraintTop_toBottomOf="@+id/cardViewIncome">
//
//        <TextView
//        android:id="@+id/tvAddIncome"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:layout_gravity="center"
//        android:text="@string/add_income"
//        android:textSize="16sp" />
//
//        </androidx.cardview.widget.CardView>
//
//        <androidx.cardview.widget.CardView
//        android:id="@+id/transactionCard"
//        android:layout_width="120dp"
//        android:layout_height="120dp"
//        android:layout_marginTop="4dp"
//        android:backgroundTint="@color/background"
//        app:cardCornerRadius="20dp"
//        app:layout_constraintEnd_toStartOf="@+id/AddIncomeCard"
//        app:layout_constraintHorizontal_bias="0.5"
//        app:layout_constraintStart_toStartOf="parent"
//        app:layout_constraintTop_toBottomOf="@+id/cardViewIncome">
//
//        <TextView
//        android:id="@+id/tvTransaction"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:layout_gravity="center"
//        android:text="@string/transactions"
//        android:textSize="16sp" />
//
//        </androidx.cardview.widget.CardView>
//
//        <androidx.cardview.widget.CardView
//        android:id="@+id/AddExpenseCard"
//        android:layout_width="120dp"
//        android:layout_height="120dp"
//        android:layout_marginTop="40dp"
//        android:backgroundTint="@color/background"
//        app:cardCornerRadius="20dp"
//        app:layout_constraintEnd_toStartOf="@+id/StatisticsCard"
//        app:layout_constraintHorizontal_bias="0.5"
//        app:layout_constraintStart_toStartOf="parent"
//        app:layout_constraintTop_toBottomOf="@+id/transactionCard">
//
//        <TextView
//        android:id="@+id/tvAddExpense"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:layout_gravity="center"
//        android:text="@string/add_expense"
//        android:textSize="16sp" />
//
//        </androidx.cardview.widget.CardView>
//
//        <androidx.cardview.widget.CardView
//        android:id="@+id/StatisticsCard"
//        android:layout_width="120dp"
//        android:layout_height="120dp"
//        android:layout_marginTop="40dp"
//        android:backgroundTint="@color/background"
//        app:cardCornerRadius="20dp"
//        app:layout_constraintEnd_toEndOf="parent"
//        app:layout_constraintHorizontal_bias="0.5"
//        app:layout_constraintStart_toEndOf="@+id/AddExpenseCard"
//        app:layout_constraintTop_toBottomOf="@+id/AddIncomeCard">
//
//        <TextView
//        android:id="@+id/tvStatistics"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:layout_gravity="center"
//        android:text="@string/statistics"
//        android:textSize="16sp" />
//
//        </androidx.cardview.widget.CardView>
        setupPieChart()
        loadPieChartData()

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
        data.setValueFormatter(PercentFormatter(bindingA.homeMainPiechart))

        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        bindingA.homeMainPiechart.data =  data
        bindingA.homeMainPiechart.invalidate();

        bindingA.homeMainPiechart.animateY(1400, Easing.EaseInOutQuad);

    }
    private fun setupPieChart() {
        bindingA.homeMainPiechart.isDrawHoleEnabled = true
        bindingA.homeMainPiechart.setUsePercentValues(true)
        bindingA.homeMainPiechart.setEntryLabelTextSize(12f)
        bindingA.homeMainPiechart.setEntryLabelColor(Color.BLACK)
        bindingA.homeMainPiechart.centerText = "Spending by Category"
        bindingA.homeMainPiechart.setCenterTextSize(24f)
        bindingA.homeMainPiechart.description.isEnabled = false
        val legend=  bindingA.homeMainPiechart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.setDrawInside(false)
        legend.isEnabled = true
    }


}