package com.example.android__theaudiodb.exposition

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.domain.artist.Artist
import com.example.android__theaudiodb.exposition.adapter.AlbumsRecyclerViewAdapter
import com.example.android__theaudiodb.exposition.adapter.ArtistsRecyclerViewAdapter
import com.example.android__theaudiodb.exposition.common.FileUtils
import com.example.android__theaudiodb.exposition.viewmodel.AlbumsViewModel
import com.example.android__theaudiodb.exposition.viewmodel.ArtistViewModel
import com.example.android__theaudiodb.infrastructure.InMemoryAlbums
import com.example.android__theaudiodb.infrastructure.InMemoryArtists
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArtistViewFragment : Fragment(R.layout.fragment_artist_view) {

    private var artist: Artist? = null
    private val artistViewModel: ArtistViewModel by activityViewModels()
    private val albumsViewModel: AlbumsViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpArtist()
        FileUtils.hideMenu(view)
        setUpAlbumsRecyclerView(view)
        setUpFavTracksRecyclerView(view)
        setUpBackBtn(view)
        setUpView(view)
        setUpLikeBtn(view)
        FileUtils.hideMenu(view)
    }

    private fun setUpArtist() {
        artist = arguments?.get("artist") as Artist?
        if (artist?.favorite === null)
            this.artist?.favorite = false
    }

    private fun setUpView(view: View) {
        when(artist?.favorite){
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


    }

    private fun setUpLikeBtn(view: View) {
        view.findViewById<ImageView>(R.id.artist_view_like).setOnClickListener {
            this.artist?.favorite = !this.artist?.favorite!!

            when (artist?.favorite) {
                true -> {
                    artistViewModel.updateArtist(this.artist!!)
                    view.findViewById<ImageView>(R.id.artist_view_heart).setImageResource(R.drawable.like_on)
                }
                false -> {

                    artistViewModel.updateArtist(this.artist!!)
                    view.findViewById<ImageView>(R.id.artist_view_heart).setImageResource(R.drawable.like_off)
                }
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
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                albumsViewModel.getAllAlbumsByArtistId(artist?.id!!)
            }
        }
        albumsViewModel.albums.observe(viewLifecycleOwner) {
            view.findViewById<RecyclerView>(R.id.artist_albums).apply {
                adapter = AlbumsRecyclerViewAdapter(it, "ArtistViewFragment")
                view.findViewById<TextView>(R.id.artist_view_album_count).text = "Album (" +  it.size + ")" // Artiste nombre d'album
                layoutManager = LinearLayoutManager(activity)
            }
        }

    }

    private fun setUpFavTracksRecyclerView(view: View) {
        view.findViewById<RecyclerView>(R.id.artist_fav_tracks).apply {
            adapter = ArtistsRecyclerViewAdapter(InMemoryArtists.getAll(), "ArtistViewFragment")
            layoutManager = LinearLayoutManager(activity)
        }
    }
}