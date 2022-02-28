package com.example.android__theaudiodb.exposition.album

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.exposition.shared.adapter.FileUtils.Companion.hideMenu
import com.example.android__theaudiodb.exposition.shared.adapter.FileUtils.Companion.showMenu
import com.example.android__theaudiodb.exposition.shared.adapter.MusicTitlesRecyclerViewAdapter
import com.example.android__theaudiodb.infrastructure.InMemoryArtists

class AlbumViewFragment : Fragment(R.layout.fragment_album_view) {

    private var sourceDestination: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.sourceDestination = arguments?.get("sourceDestination") as String
        setUpRecyclerView(view)
        setUpBackBtn(view, sourceDestination!!)
        view.findViewById<ImageView>(R.id.lyrics_view_img).setImageResource(R.drawable.artist)
        hideMenu(view)
    }

    private fun setUpBackBtn(view: View, sourceDestination: String) {
        view.findViewById<ImageView>(R.id.album_view_back).setOnClickListener {
            showMenu(view)
            when(sourceDestination) {
                "SearchingFragment" -> Navigation.findNavController(it).navigate(R.id.action_albumViewFragment_to_searchingFragment)
                "FavoritesFragment" -> Navigation.findNavController(it).navigate(R.id.action_albumViewFragment_to_favoritesFragment)
                "RankingTabAlbumsFragment" -> Navigation.findNavController(it).navigate(R.id.action_albumViewFragment_to_itemFragment)
            }
        }
    }

    private fun setUpRecyclerView(view: View) {
        view.findViewById<RecyclerView>(R.id.album_view_albums).apply {
            adapter = MusicTitlesRecyclerViewAdapter(InMemoryArtists.getAll(), "AlbumViewFragment")
            layoutManager = LinearLayoutManager(activity)
        }
    }
}