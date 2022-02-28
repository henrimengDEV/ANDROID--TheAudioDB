package com.example.android__theaudiodb.exposition.ranking

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.exposition.shared.adapter.AlbumsRecyclerViewAdapter
import com.example.android__theaudiodb.infrastructure.InMemoryAlbumsRepository

class RankingTabAlbumsFragment : Fragment(R.layout.fragment_ranking_title) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerView(view)
    }

    fun setUpRecyclerView(view: View) {
        view.findViewById<RecyclerView>(R.id.music_titles).apply {
            adapter = AlbumsRecyclerViewAdapter(InMemoryAlbumsRepository.getAll(), "RankingTabAlbumsFragment")
            layoutManager = LinearLayoutManager(activity)
        }
    }
}