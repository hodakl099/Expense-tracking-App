package com.example.trackingapp.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackingapp.ui.data.Transaction
import com.example.trackingapp.ui.repository.TransactionRepository
import com.example.trackingapp.ui.utils.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class TransactionViewModel(private val transactionRepo: TransactionRepository) : ViewModel() {



    private val _transactionFilter = MutableStateFlow("Overall")
    val transactionFilter: StateFlow<String> = _transactionFilter

    private val _uiState = MutableStateFlow<ViewState>(ViewState.Loading)
    // UI collect from this stateFlow to get the state updates
    private val uiState : StateFlow<ViewState> = _uiState

    //insert transaction
    fun insetTransaction(transaction: Transaction) = viewModelScope.launch {
        transactionRepo.insert(transaction)
    }

    // update transaction
    fun updateTransaction(transaction: Transaction) = viewModelScope.launch {
        transactionRepo.update(transaction)
    }

    // delete transaction
    fun deleteTransaction(transaction: Transaction) = viewModelScope.launch {
        transactionRepo.delete(transaction)
    }

    // get all transaction
    fun getAllTransaction(type: String) = viewModelScope.launch {
        transactionRepo.getAllSingleTransaction(type).collect { result ->
            if (result.isNullOrEmpty()) {
                _uiState.value = ViewState.Empty
            } else {
                _uiState.value = ViewState.Success(result)
            }
        }
    }

    // to filter income
    fun getAllIncome() {
        _transactionFilter.value = "Income"
    }

    // to filter Expense
    fun getAllExpense() {
        _transactionFilter.value = "Expense"
    }






}