package com.example.trackingapp.ui.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.trackingapp.R
import com.example.trackingapp.databinding.ActivityMainBinding
import com.example.trackingapp.ui.repository.TransactionRepository
import com.example.trackingapp.ui.viewmodel.TransactionViewModel
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: TransactionRepository


    private val viewModel: TransactionViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    private lateinit var drawerLayout: DrawerLayout

    private lateinit var navView: NavigationView

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bindingActivity = ActivityMainBinding.inflate(layoutInflater)
        binding = bindingActivity
        val view = binding.root
        setContentView(view)



        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.findNavController()
        drawerLayout = binding.drawerLayout
        navView = binding.drawerNavView
        navView.setupWithNavController(navController)

        setSupportActionBar(binding.toolbar)
        appBarConfiguration = AppBarConfiguration(
            navController.graph, drawerLayout
        )

        // this to make navigate up when on different fragment
        binding.toolbar.setupWithNavController(navController, drawerLayout)

        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}