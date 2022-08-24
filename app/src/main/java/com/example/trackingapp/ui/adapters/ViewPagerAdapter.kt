package com.example.trackingapp.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(var listOfCards: MutableList<View>) : RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>(){

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int {
        return listOfCards.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


}