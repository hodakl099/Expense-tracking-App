package com.example.trackingapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.example.trackingapp.databinding.ItemCardBinding

class ViewPagerAdapter(var listOfCards: MutableList<CardView>) : RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>(){

    inner class Pager2ViewHolder(var binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return listOfCards.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return Pager2ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        holder.binding.cardViewItem[position]
    }
}