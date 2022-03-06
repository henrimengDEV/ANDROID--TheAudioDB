package com.example.android__theaudiodb.exposition.album

import android.os.Bundle
import android.view.View
import android.widget.ImageView
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
import com.example.android__theaudiodb.exposition.shared.FileUtils.Companion.hideMenu
import com.example.android__theaudiodb.exposition.shared.adapter.TracksRecyclerViewAdapter
import com.example.android__theaudiodb.exposition.shared.viewmodel.TracksViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlbumViewFragment : Fragment(R.layout.fragment_album_view) {

    private val viewModel: TracksViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerView(view)
        setUpBackBtn(view)
        view.findViewById<ImageView>(R.id.lyrics_view_img).setImageResource(R.drawable.artist)
        hideMenu(view)
    }

    private fun setUpBackBtn(view: View) {
        view.findViewById<ImageView>(R.id.album_view_back).setOnClickListener {
            Navigation.findNavController(view).navigateUp()
        }
    }

    //TODO Grosse bidouille
    private fun setUpRecyclerView(view: View) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getTopFiftyTracks()
            }
        }
        viewModel.tracks.observe(viewLifecycleOwner) {
            view.findViewById<RecyclerView>(R.id.album_view_albums).apply {
                adapter = TracksRecyclerViewAdapter(it, "AlbumViewFragment")
                layoutManager = LinearLayoutManager(activity)
            }
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.loading.observe(viewLifecycleOwner) {
//            if (it)
//                loadingProgress.visibility = View.VISIBLE
//            else
//                loadingProgress.visibility = View.INVISIBLE
        }

    }
}