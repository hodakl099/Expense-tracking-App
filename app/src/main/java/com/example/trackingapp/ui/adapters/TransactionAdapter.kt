package com.example.trackingapp.ui.adapters


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trackingapp.R
import com.example.trackingapp.databinding.TransactionItemBinding
import com.example.trackingapp.ui.data.entity.Transaction
import com.example.trackingapp.ui.utility.formatCurrency



class TransactionAdapter
    : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {


    inner class TransactionViewHolder(val binding : TransactionItemBinding) : RecyclerView.ViewHolder(binding.root)



    private  val differCallBack = object : DiffUtil.ItemCallback<Transaction>() {
        override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {

        val binding = TransactionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TransactionViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {
          val itemValue =  formatCurrency(item.amount)

                transactionText.text = itemValue
                tvTransactionDescription.text = holder.itemView.context.getString(item.category.categoryDescription)
                ivTransactionIcon.setImageResource(item.category.categoryIcon)


                when (item.transactionType) {
                    "Income" ->  {
                        transactionText.setTextColor(
                            ContextCompat.getColor(
                                transactionText.context,
                                R.color.holo_green_light
                            )
                        ).also {
                            tvTransactionTitle.text = "Income"
                        }


                    }
                    "Expense" ->  {
                        transactionText.setTextColor(
                            ContextCompat.getColor(
                                transactionText.context,
                                R.color.holo_red_light
                            )
                        ).also {
                            tvTransactionTitle.text = "Expense"
                        }
                    }

            }

            holder.itemView.setOnClickListener{
                onItemClickListener?.let {
                    it(item)
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    private var onItemClickListener : ((Transaction) -> Unit)? = null

    fun setOnItemClickListener(listener : (Transaction) -> Unit) {
        onItemClickListener = listener
    }



}