package com.example.trackingapp.ui.ui.menufragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.trackingapp.databinding.FragmentSettingBinding
import com.example.trackingapp.ui.viewmodel.TransactionViewModel
import com.google.android.material.shape.ShapeAppearanceModel.builder
import java.util.stream.DoubleStream.builder
import java.util.stream.Stream.builder
import android.app.AlertDialog.Builder
import android.content.DialogInterface


class SettingFragment : Fragment() {


    private lateinit var binding: FragmentSettingBinding

    private lateinit var transactionViewModel: TransactionViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(layoutInflater, container, false)

        transactionViewModel =
            ViewModelProvider(requireActivity())[TransactionViewModel::class.java]


        binding.deleteAllRecords.tvDeleteRecords.setOnClickListener {

            val alertDialog = Builder(requireContext())
            alertDialog.setMessage("Do you want to delete all transaction ?")
                .setCancelable(false)
                .setPositiveButton("Proceed") { _, _ ->
                    deleteAll()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.cancel()
                }
            // create dialog box
            val alert = alertDialog.create()
            // set title for alert dialog box
            alert.setTitle("Delete All Transactions")
            // show alert dialog
            alert.show()


        }


        return binding.root
    }

    private fun deleteAll() {
        transactionViewModel.deleteAllTransactions()
        Toast.makeText(requireContext(),"All transactions are deleted",Toast.LENGTH_SHORT).show()
    }

}