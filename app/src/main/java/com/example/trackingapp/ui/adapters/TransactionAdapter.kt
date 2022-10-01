package com.example.trackingapp.ui.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.trackingapp.databinding.TransactionItemBinding
import com.example.trackingapp.ui.data.Transaction


class TransactionAdapter(private val transactionList: MutableList<Transaction>,private val context: Context) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {


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
        holder.itemView.setOnClickListener{
            Toast.makeText(context, "Yay", Toast.LENGTH_LONG).show()
        }

    }

    override fun getItemCount(): Int {
        return transactionList.size
    }


}
