package com.example.android__theaudiodb.exposition

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.exposition.common.FileUtils
import com.example.android__theaudiodb.exposition.adapter.AlbumsRecyclerViewAdapter
import com.example.android__theaudiodb.exposition.adapter.ArtistsRecyclerViewAdapter
import com.example.android__theaudiodb.exposition.viewmodel.AlbumsViewModel
import com.example.android__theaudiodb.exposition.viewmodel.ArtistViewModel
import com.example.android__theaudiodb.infrastructure.InMemoryArtists
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val albumsViewModel: AlbumsViewModel by activityViewModels()
    private val artistViewModel: ArtistViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FileUtils.showMenu(view)
        setUpArtistsRecyclerView(view)
        setUpAlbumsRecyclerView(view)
    }

    private fun setUpArtistsRecyclerView(view: View) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                artistViewModel.getFavoritesArtist()
            }
        }
        artistViewModel.artist.observe(viewLifecycleOwner) {
            view.findViewById<RecyclerView>(R.id.favorites_artists).apply {
                adapter = ArtistsRecyclerViewAdapter(it, "FavoritesFragment")
                layoutManager = LinearLayoutManager(activity)
            }
        }

        artistViewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        artistViewModel.loading.observe(viewLifecycleOwner) {
//            if (it)
//                loadingProgress.visibility = View.VISIBLE
//            else
//                loadingProgress.visibility = View.INVISIBLE
        }



    }

    private fun setUpAlbumsRecyclerView(view: View) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                albumsViewModel.getFavoritesAlbums()
            }
        }
        albumsViewModel.albums.observe(viewLifecycleOwner) {
            view.findViewById<RecyclerView>(R.id.favorites_albums).apply {
                adapter = AlbumsRecyclerViewAdapter(it, "FavoritesFragment")
                layoutManager = LinearLayoutManager(activity)
            }
        }
        albumsViewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        albumsViewModel.loading.observe(viewLifecycleOwner) {
//            if (it)
//                loadingProgress.visibility = View.VISIBLE
//            else
//                loadingProgress.visibility = View.INVISIBLE
        }
    }
}