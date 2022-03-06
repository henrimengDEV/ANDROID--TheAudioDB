package com.example.android__theaudiodb.exposition

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.domain.artist.Artist
import com.example.android__theaudiodb.domain.track.Track
import com.example.android__theaudiodb.exposition.adapter.AlbumsRecyclerViewAdapter
import com.example.android__theaudiodb.exposition.adapter.ArtistsRecyclerViewAdapter
import com.example.android__theaudiodb.exposition.common.FileUtils
import com.example.android__theaudiodb.infrastructure.InMemoryAlbums
import com.example.android__theaudiodb.infrastructure.InMemoryArtists
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistViewFragment : Fragment(R.layout.fragment_artist_view) {

    private var artist: Artist? = null
    private var like: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        artist = arguments?.get("artist") as Artist?

        FileUtils.hideMenu(view)
        setUpAlbumsRecyclerView(view)
        setUpFavTracksRecyclerView(view)
        setUpBackBtn(view)
        setUpView(view)
        setUpLikeBtn(view)
        FileUtils.hideMenu(view)
    }

    private fun setUpView(view: View) {
        when(like){
            true -> view.findViewById<ImageView>(R.id.artist_view_heart).setImageResource(R.drawable.like_on)
            false -> view.findViewById<ImageView>(R.id.artist_view_heart).setImageResource(R.drawable.like_off)
        }
        Picasso.get()
            .load(this.artist?.artistLogoURL)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .networkPolicy(NetworkPolicy.NO_CACHE)
            .error(R.drawable.ic_no_image)
            .noFade()
            .into(view.findViewById<ImageView>(R.id.artist_view_img))

        view.findViewById<TextView>(R.id.artist_view_description_name).text = this.artist?.name// Artiste nom
        view.findViewById<TextView>(R.id.artist_view_description_city).text = this.artist?.country // Artiste ville
        view.findViewById<TextView>(R.id.artist_view_bio).text = this.artist?.biographyFR // Artiste bio
        view.findViewById<TextView>(R.id.artist_view_album_count).text = "Album (" + 12 + ")" // Artiste nombre d'album


    }

    private fun setUpLikeBtn(view: View) {
        view.findViewById<ImageView>(R.id.artist_view_like).setOnClickListener {
            this.like = !this.like

            when(like){
                true -> view.findViewById<ImageView>(R.id.artist_view_heart).setImageResource(R.drawable.like_on)
                false -> view.findViewById<ImageView>(R.id.artist_view_heart).setImageResource(R.drawable.like_off)
            }
        }

    }


    //Back Button
    private fun setUpBackBtn(view: View) {
        view.findViewById<ImageView>(R.id.artist_view_back).setOnClickListener {
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
            adapter = ArtistsRecyclerViewAdapter(InMemoryArtists.getAll(), "ArtistViewFragment")
            layoutManager = LinearLayoutManager(activity)
        }
    }
}