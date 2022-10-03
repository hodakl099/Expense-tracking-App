package com.example.trackingapp.ui.utils

import com.example.trackingapp.ui.data.Transaction

sealed class ViewState {
    object  Loading : ViewState()
    object Empty : ViewState()
    data class Success(val transaction: List<Transaction>) : ViewState()
    data class Error(val exception: Throwable) : ViewState()
}