package com.example.android__theaudiodb.exposition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.infrastructure.APIRepository
import com.example.android__theaudiodb.domain.artist.Artists
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
