package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.get
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.plant(Timber.DebugTree())
        Timber.i("onCreate")
        setSupportActionBar(binding.toolbar);
        navController = this.findNavController(R.id.myNavHost)
        appBarConfiguration = AppBarConfiguration(navController.graph, null)
        NavigationUI.setupActionBarWithNavController(this, navController)
        NavigationUI.setupWithNavController(binding.navView, navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == controller.graph[R.id.shoeListFragment].id
                || destination.id == controller.graph[R.id.loginFragment].id
            )
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            else
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHost)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}
