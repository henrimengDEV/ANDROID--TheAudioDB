package com.example.android__theaudiodb.exposition.artists

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.exposition.shared.adapter.AlbumsRecyclerViewAdapter
import com.example.android__theaudiodb.infrastructure.InMemoryAlbums

class ArtistViewFragment : Fragment(R.layout.fragment_artist_view) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpAlbumsRecyclerView(view)
//        setUpFavTracksRecyclerView(view)
        view.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.action_artistFragment_to_searchingFragment)
        }
    }

    private fun setUpAlbumsRecyclerView(view: View) {
        view.findViewById<RecyclerView>(R.id.artist_albums).apply {
            adapter = AlbumsRecyclerViewAdapter(InMemoryAlbums.getAll())
            layoutManager = LinearLayoutManager(activity)
        }
    }

//    private fun setUpFavTracksRecyclerView(view: View) {
//        view.findViewById<RecyclerView>(R.id.artist_fav_tracks).apply {
//            adapter = AlbumsRecyclerViewAdapter(InMemoryAlbums.getAll())
//            layoutManager = LinearLayoutManager(activity)
//        }
//    }

}