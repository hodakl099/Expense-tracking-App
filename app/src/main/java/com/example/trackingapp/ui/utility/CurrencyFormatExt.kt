package com.example.trackingapp.ui.utility

import java.text.DecimalFormat
import java.util.*

fun formatCurrency(amount: Double): String {
    val decimalFormat = DecimalFormat(Constants.FORMAT_PATTERN)
    decimalFormat.minimumFractionDigits = Constants.MINIMUM_FRACTION_DIGITS
    val amountData = java.lang.Double.valueOf(amount)
    val value = decimalFormat.format(amountData)
    val currency = Currency.getInstance(Constants.CURRENCY_CODE)
    val symbol = currency.symbol
    return String.format("$symbol${value}", "%.2f")
}