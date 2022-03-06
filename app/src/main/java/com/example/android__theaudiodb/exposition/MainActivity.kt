package com.example.android__theaudiodb.exposition

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.room.Room
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.infrastructure.sqlite.AppDatabase
import com.example.android__theaudiodb.infrastructure.sqlite.entity.ArtistEntity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        NavigationUI.setupWithNavController(
            findViewById<BottomNavigationView>(R.id.menu_app),
            navHost.navController
        )

        GlobalScope.launch {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()

            val artistDao = db.artistDAO()
            println(artistDao.getAll())
            println(artistDao.getById(1))
        }
    }

    // coroutine -> DTO -> persistence en BDD
}
