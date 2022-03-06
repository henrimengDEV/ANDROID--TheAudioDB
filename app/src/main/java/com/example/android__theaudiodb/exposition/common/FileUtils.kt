package com.example.android__theaudiodb.exposition.shared

import android.view.View
import android.view.ViewGroup
import com.example.android__theaudiodb.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class FileUtils {

    companion object {
        fun hideMenu(view: View) {
            val parent: ViewGroup = view.parent.parent.parent as ViewGroup;
            parent.findViewById<BottomNavigationView>(R.id.menu_app).visibility = View.GONE
        }

        fun showMenu(view: View) {
            val parent: ViewGroup = view.parent.parent.parent as ViewGroup;
            parent.findViewById<BottomNavigationView>(R.id.menu_app).visibility = View.VISIBLE
        }
    }
}