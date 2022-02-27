package com.example.android__theaudiodb.exposition.album

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.exposition.shared.adapter.MusicTitlesRecyclerViewAdapter
import com.example.android__theaudiodb.infrastructure.InMemoryArtists
import com.google.android.material.bottomnavigation.BottomNavigationView

class AlbumViewFragment : Fragment(R.layout.fragment_album_view) {

    private var sourceDestination: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.sourceDestination = arguments?.get("sourceDestination") as String
        setUpRecyclerView(view)
    }

    fun setUpRecyclerView(view: View) {
        view.findViewById<RecyclerView>(R.id.album_view_albums).apply {
            adapter = MusicTitlesRecyclerViewAdapter(InMemoryArtists.getAll(), "AlbumViewFragment")
            layoutManager = LinearLayoutManager(activity)
        }
    }
}