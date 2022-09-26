package com.example.trackingapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trackingapp.databinding.ActivityMainBinding.inflate
import com.example.trackingapp.databinding.TransactionItemBinding
import com.example.trackingapp.ui.data.Transaction
import com.example.trackingapp.ui.viewmodel.TransactionViewModel

class TransactionAdapter(private val transactionList: MutableList<Transaction>) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {


    inner class TransactionViewHolder(val binding : TransactionItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {

        val binding = TransactionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.binding.apply {
            val currentItem = transactionList[position]

            transactionText.text = currentItem.transactionAmount
        }
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }
}