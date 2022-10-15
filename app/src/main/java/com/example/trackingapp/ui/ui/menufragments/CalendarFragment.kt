package com.example.trackingapp.ui.ui.menufragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trackingapp.databinding.FragmentCalendarBinding




class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
      val  calendarFragment = FragmentCalendarBinding.inflate(layoutInflater)
         binding = calendarFragment
        return binding.root
    }
}
