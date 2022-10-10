package com.example.trackingapp.ui.utility

import java.text.DecimalFormat
import java.util.*

fun formatCurrency(amount: Double): String {
    val decimalFormat = DecimalFormat("#,###.##")
    decimalFormat.minimumFractionDigits = 2
    val amountData = java.lang.Double.valueOf(amount)
    val value = decimalFormat.format(amountData)
    val currency = Currency.getInstance("USD")
    val symbol = currency.symbol
    return String.format("$symbol${value}", "%.2f")
}