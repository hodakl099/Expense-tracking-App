package com.example.trackingapp.ui.utility

import android.content.Context
import com.example.trackingapp.R
import com.example.trackingapp.ui.utils.TransactionCategory

class Constants(context : Context) {

    val TRANSACTION_CATEGORTY = arrayListOf(
        context.getString(TransactionCategory.Bills.categoryDescription),
        context.getString(TransactionCategory.Food.categoryDescription),
        context.getString(TransactionCategory.Education.categoryDescription),
        context.getString(TransactionCategory.Entertainment.categoryDescription),
        context.getString(TransactionCategory.Housing.categoryDescription),
        context.getString(TransactionCategory.Health.categoryDescription),
        context.getString(TransactionCategory.Travel.categoryDescription),
        context.getString(TransactionCategory.Transportation.categoryDescription),
        context.getString(TransactionCategory.Shopping.categoryDescription),
        context.getString(TransactionCategory.Salary.categoryDescription),
        context.getString(TransactionCategory.Investments.categoryDescription),
        context.getString(TransactionCategory.Other.categoryDescription)
    )


    val PAYMENT_METHOD = arrayListOf(
        context.getString(R.string.cash),
        context.getString(R.string.online_banking),
        context.getString(R.string.credit_card),
        context.getString(R.string.debit_card),
    )

}