package com.example.android__theaudiodb.exposition

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.android__theaudiodb.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        NavigationUI.setupWithNavController(
            findViewById<BottomNavigationView>(R.id.menu_app),
            navHost.navController
        )
    }
}
