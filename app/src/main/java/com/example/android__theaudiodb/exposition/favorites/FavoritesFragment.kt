package com.example.android__theaudiodb.exposition.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.exposition.shared.adapter.AlbumsRecyclerViewAdapter
import com.example.android__theaudiodb.exposition.shared.adapter.ArtistsRecyclerViewAdapter
import com.example.android__theaudiodb.infrastructure.InMemoryAlbums
import com.example.android__theaudiodb.infrastructure.InMemoryArtists

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpArtistsRecyclerView(view)
        setUpAlbumsRecyclerView(view)
    }

    private fun setUpArtistsRecyclerView(view: View) {
        view.findViewById<RecyclerView>(R.id.favorites_artists).apply {
            adapter = ArtistsRecyclerViewAdapter(InMemoryArtists.getAll())
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun setUpAlbumsRecyclerView(view: View) {
        view.findViewById<RecyclerView>(R.id.favorites_albums).apply {
            adapter = AlbumsRecyclerViewAdapter(InMemoryAlbums.getAll())
            layoutManager = LinearLayoutManager(activity)
        }
    }
}