package com.example.trackingapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.example.trackingapp.R
import com.example.trackingapp.databinding.ItemCardBinding

class ViewPagerAdapter(var listOfCards: MutableList<CardView>) : RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>(){

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var cardView : CardView = itemView.findViewById(R.id.cardViewItem)
    }

    override fun getItemCount(): Int {
        return listOfCards.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {

        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_card,parent,false))
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        holder.cardView = listOfCards[position]
    }
}