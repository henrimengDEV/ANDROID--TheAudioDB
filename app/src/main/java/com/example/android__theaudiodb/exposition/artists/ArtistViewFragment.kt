package com.example.android__theaudiodb.exposition.artists

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.exposition.shared.adapter.AlbumsRecyclerViewAdapter
import com.example.android__theaudiodb.exposition.shared.adapter.FileUtils
import com.example.android__theaudiodb.exposition.shared.adapter.MusicTitlesRecyclerViewAdapter
import com.example.android__theaudiodb.infrastructure.InMemoryAlbums
import com.example.android__theaudiodb.infrastructure.InMemoryArtists

class ArtistViewFragment : Fragment(R.layout.fragment_artist_view) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpAlbumsRecyclerView(view)
        setUpFavTracksRecyclerView(view)
        setUpBackBtn(view)
        view.findViewById<ImageView>(R.id.artist_view_img).setImageResource(R.drawable.artist) // Artiste image
        view.findViewById<TextView>(R.id.artist_view_description_name).text = "nom de l'artiste"// Artiste nom
        view.findViewById<TextView>(R.id.artist_view_description_city).text = "City" // Artiste ville
        view.findViewById<TextView>(R.id.artist_view_bio).text = "bio" // Artiste bio
        view.findViewById<TextView>(R.id.artist_view_album_count).text = "Album (" + 12 + ")" // Artiste nombre d'album

        FileUtils.hideMenu(view)
    }

    //Back Button
    private fun setUpBackBtn(view: View) {
        view.findViewById<ImageView>(R.id.artist_view_back).setOnClickListener {
            FileUtils.showMenu(view)
            Navigation.findNavController(view).navigateUp()
        }
    }

    // Recycler
    private fun setUpAlbumsRecyclerView(view: View) {
        view.findViewById<RecyclerView>(R.id.artist_albums).apply {
            adapter = AlbumsRecyclerViewAdapter(InMemoryAlbums.getAll(), "ArtistViewFragment")
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun setUpFavTracksRecyclerView(view: View) {
        view.findViewById<RecyclerView>(R.id.artist_fav_tracks).apply {
            adapter = MusicTitlesRecyclerViewAdapter(InMemoryArtists.getAll(), "ArtistViewFragment")
            layoutManager = LinearLayoutManager(activity)
        }
    }
}