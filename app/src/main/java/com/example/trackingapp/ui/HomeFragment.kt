package com.example.trackingapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.example.trackingapp.R
import com.example.trackingapp.databinding.FragmentHomeBinding
import com.example.trackingapp.databinding.ItemCardBinding
import com.example.trackingapp.ui.adapters.ViewPagerAdapter


class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null

    private val bindig get() = _binding!!

    private val card : ItemCardBinding? = null

    private var cardsList = mutableListOf<CardView>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val root = bindig.root



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postToList()

        bindig.viewPager2.adapter = ViewPagerAdapter(cardsList)


    }

    private fun addCardView(card : CardView) {
        cardsList.add(card)
    }

    private fun postToList(){
        for (i in 1..5) {
            card?.cardViewItem?.let { addCardView(it) }
        }
    }
}