package com.example.android__theaudiodb.exposition.ranking

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.exposition.shared.adapter.AlbumsRecyclerViewAdapter
import com.example.android__theaudiodb.infrastructure.InMemoryAlbums
import com.example.android__theaudiodb.infrastructure.InMemoryArtists

class RankingTabAlbumsFragment : Fragment(R.layout.fragment_ranking_title) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerView(view)
    }

    fun setUpRecyclerView(view: View) {
        view.findViewById<RecyclerView>(R.id.music_titles).apply {
            adapter = AlbumsRecyclerViewAdapter(InMemoryAlbums.getAll())
            layoutManager = LinearLayoutManager(activity)
        }
    }
}