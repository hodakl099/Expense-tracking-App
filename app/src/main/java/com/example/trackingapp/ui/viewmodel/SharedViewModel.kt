package com.example.trackingapp.ui.viewmodel

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat
import java.util.*

class SharedViewModel(val savedStateHandle: SavedStateHandle) : ViewModel() {



    private val _argument = MutableLiveData<String>()
    val argument: LiveData<String> = _argument

    private val _textView = MutableLiveData<TextView>()
    val textView: LiveData<TextView> = _textView



    //function to format the currency.
      fun formatArgumentCurrency(argument : String, textView: TextView) {

        _argument.value = argument
        _textView.value = textView

        val valueText = savedStateHandle.get<String>(argument)
        val dec = DecimalFormat("#,###.##")
        val number = valueText?.let { java.lang.Double.valueOf(it) }
        val value = dec.format(number)
        val currency = Currency.getInstance("USD")
        val symbol = currency.symbol
        textView.text = String.format("$symbol$value","%.2f" )


    }


}