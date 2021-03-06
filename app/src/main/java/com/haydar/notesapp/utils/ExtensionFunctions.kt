package com.haydar.notesapp.utils

import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.haydar.notesapp.MainActivity
import com.haydar.notesapp.R

object ExtensionFunctions {
    fun MaterialToolbar.setActionBar(activity: FragmentActivity){
        val navController =findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupWithNavController(navController,appBarConfiguration)
        (activity as MainActivity).setSupportActionBar(this)
        navController.addOnDestinationChangedListener{_, destination, _ ->
            when(destination.id){
                R.id.updateFragment -> this.setNavigationIcon(R.drawable.ic_left_arrow)
                R.id.addFragment -> this.setNavigationIcon(R.drawable.ic_left_arrow)
                R.id.detailFragment -> this.setNavigationIcon(R.drawable.ic_left_arrow)
            }
        }
    }
}