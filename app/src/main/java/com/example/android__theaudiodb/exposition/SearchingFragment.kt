package com.example.android__theaudiodb.exposition

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.exposition.common.FileUtils
import com.example.android__theaudiodb.exposition.viewmodel.AlbumsViewModel
import com.example.android__theaudiodb.exposition.viewmodel.ArtistViewModel
import com.example.android__theaudiodb.exposition.adapter.AlbumsRecyclerViewAdapter
import com.example.android__theaudiodb.exposition.adapter.ArtistsRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchingFragment : Fragment(R.layout.fragment_searching) {

    private val albumsViewModel: AlbumsViewModel by activityViewModels()
    private val artistsViewModel: ArtistViewModel by activityViewModels()

    private var queryTextListener: SearchView.OnQueryTextListener? = null

    var searchInput: String = "Ed sheeran"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FileUtils.showMenu(view)
        setUpArtistsRecyclerView(view)
        setUpAlbumsRecyclerView(view)

        val searchView: SearchView = view.findViewById<SearchView>(R.id.search_bar)
        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        queryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    searchView.clearFocus()
                    searchInput = ""
                    setUpArtistsRecyclerView(view)
                    setUpAlbumsRecyclerView(view)
                }

                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                searchView.clearFocus()
                searchInput = query
                setUpArtistsRecyclerView(view)
                setUpAlbumsRecyclerView(view)
                return true
            }
        }

        searchView.setOnQueryTextListener(queryTextListener)

    }

    private fun setUpArtistsRecyclerView(view: View) {
        val loadingProgress = view.findViewById<ProgressBar>(R.id.indeterminate_Bar_search)
        val artistRecyclerView = view.findViewById<RecyclerView>(R.id.searching_artists)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                if (searchInput.isNotEmpty())
                    artistsViewModel.getArtist(searchInput)
            }
        }
        artistsViewModel.artist.observe(viewLifecycleOwner) {
            artistRecyclerView.apply {
                if (it != null) {
                    adapter = ArtistsRecyclerViewAdapter(it, "SearchingFragment")
                    layoutManager = LinearLayoutManager(activity)
                }
            }
        }
        artistsViewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(context?.applicationContext, it, Toast.LENGTH_LONG).show()
            artistRecyclerView.adapter = AlbumsRecyclerViewAdapter(listOf(), "SearchingFragment")
        }
        artistsViewModel.loading.observe(viewLifecycleOwner) {
            if (it)
                loadingProgress.visibility = View.VISIBLE
            else
                loadingProgress.visibility = View.INVISIBLE
        }
    }

    private fun setUpAlbumsRecyclerView(view: View) {
        val loadingProgress = view.findViewById<ProgressBar>(R.id.indeterminate_Bar_search)
        val albumsRecyclerView = view.findViewById<RecyclerView>(R.id.searching_albums)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                albumsViewModel.getAlbumsByArtistName(searchInput)
                artistsViewModel.getArtist(searchInput)
            }
        }
        albumsViewModel.albums.observe(viewLifecycleOwner) {
            albumsRecyclerView.apply {
                if (it != null) {
                    adapter = AlbumsRecyclerViewAdapter(it, "SearchingFragment")
                    layoutManager = LinearLayoutManager(activity)
                }
            }
        }
        albumsViewModel.errorMessage.observe(viewLifecycleOwner) {
//            Toast.makeText(context?.applicationContext, it, Toast.LENGTH_LONG).show()
            albumsRecyclerView.adapter = AlbumsRecyclerViewAdapter(listOf(), "SearchingFragment")
        }
        albumsViewModel.loading.observe(viewLifecycleOwner) {
            if (it)
                loadingProgress.visibility = View.VISIBLE
            else
                loadingProgress.visibility = View.INVISIBLE
        }
    }
}