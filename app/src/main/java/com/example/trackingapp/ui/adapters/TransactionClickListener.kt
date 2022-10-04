package com.example.trackingapp.ui.adapters

import android.view.View
import com.example.trackingapp.ui.data.Transaction

interface TransactionClickListener {

    fun onTransactionClickListener(view : View, transaction: Transaction)
}