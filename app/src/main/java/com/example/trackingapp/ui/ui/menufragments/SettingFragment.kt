package com.example.trackingapp.ui.ui.menufragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.trackingapp.databinding.FragmentSettingBinding
import android.app.AlertDialog.Builder
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.trackingapp.ui.viewmodel.TransactionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment() {


    private lateinit var binding: FragmentSettingBinding

    val transactionViewModel: TransactionViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(layoutInflater, container, false)




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
        setUpNightMode()


        return binding.root
    }


    // To delete all transactions
    private fun deleteAll() {
        transactionViewModel.deleteAllTransactions()
        Toast.makeText(requireContext(),"All transactions are deleted",Toast.LENGTH_SHORT).show()
    }

    // to switch night mode to light mode and vice versa.
    private fun setUpNightMode() {
        lifecycleScope.launchWhenCreated {
            binding.switchDarkTheme.switchDarkTheme.isChecked = transactionViewModel.readUIPreference("dark_mode") == true

            binding.switchDarkTheme.switchDarkTheme.setOnCheckedChangeListener { _, DarkMode->
                lifecycleScope.launchWhenCreated {
                    if (DarkMode) {
                        transactionViewModel.saveUIPreference("dark_mode", true)
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                    else{
                        transactionViewModel.saveUIPreference("dark_mode", false)
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                }

            }
        }
    }


    //function to alert the user before deleting all transactions in database.
    private fun alertDialog() {

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

}