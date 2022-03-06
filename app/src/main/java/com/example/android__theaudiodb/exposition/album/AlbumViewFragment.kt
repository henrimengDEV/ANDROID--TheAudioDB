package com.example.android__theaudiodb.exposition.album

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.exposition.shared.adapter.ArtistsRecyclerViewAdapter
import com.example.android__theaudiodb.exposition.shared.adapter.FileUtils.Companion.hideMenu
import com.example.android__theaudiodb.exposition.shared.adapter.FileUtils.Companion.showMenu
import com.example.android__theaudiodb.infrastructure.InMemoryArtists
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumViewFragment : Fragment(R.layout.fragment_album_view) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerView(view)
        setUpBackBtn(view)
        view.findViewById<ImageView>(R.id.lyrics_view_img).setImageResource(R.drawable.artist)
        hideMenu(view)
    }

    private fun setUpBackBtn(view: View) {
        view.findViewById<ImageView>(R.id.album_view_back).setOnClickListener {
            showMenu(view)
            Navigation.findNavController(view).navigateUp()
        }
    }

    private fun setUpRecyclerView(view: View) {
        view.findViewById<RecyclerView>(R.id.album_view_albums).apply {
            adapter = ArtistsRecyclerViewAdapter(InMemoryArtists.getAll(), "AlbumViewFragment")
            layoutManager = LinearLayoutManager(activity)
        }
    }
}