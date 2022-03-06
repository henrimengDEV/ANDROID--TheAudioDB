package com.example.android__theaudiodb.exposition

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
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
import com.example.android__theaudiodb.domain.album.Album
import com.example.android__theaudiodb.exposition.common.FileUtils.Companion.hideMenu
import com.example.android__theaudiodb.exposition.adapter.TracksRecyclerViewAdapter
import com.example.android__theaudiodb.exposition.viewmodel.AlbumViewModel
import com.example.android__theaudiodb.exposition.viewmodel.TracksViewModel
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlbumViewFragment : Fragment(R.layout.fragment_album_view) {

    private var album: Album? = null
    private val tracksViewModel: TracksViewModel by activityViewModels()
    private val albumViewModel: AlbumViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpAlbum()
        setUpRecyclerView(view)
        setUpBackBtn(view)
        setUpView(view)
        setUpLikeBtn(view)
        hideMenu(view)
    }

    private fun setUpAlbum() {
        album = arguments?.get("album") as Album?
        if (album?.favorite === null)
            this.album?.favorite = "false"
    }

    private fun setUpView(view: View) {
        when (album?.favorite) {
            "true" -> view.findViewById<ImageView>(R.id.album_view_heart).setImageResource(R.drawable.like_on)
            "false" -> view.findViewById<ImageView>(R.id.album_view_heart).setImageResource(R.drawable.like_off)
        }
        view.findViewById<TextView>(R.id.album_view_name).text = this.album?.name
        view.findViewById<TextView>(R.id.album_view_description).text = this.album?.descriptionFR
        Picasso.get()
            .load(this.album?.photoURL)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .networkPolicy(NetworkPolicy.NO_CACHE)
            .error(R.drawable.ic_no_image)
            .noFade()
            .into(view.findViewById<ImageView>(R.id.album_view_img))

        if (this.album?.likes === null || this.album?.score === null) {
            view.findViewById<LinearLayout>(R.id.album_view_notation).visibility = View.GONE
        } else {
            view.findViewById<TextView>(R.id.album_view_votes_number).text =
                String.format(getString(R.string.votes), this.album?.likes)
            view.findViewById<TextView>(R.id.album_view_score).text = this.album?.score.toString()
        }
    }

    private fun setUpLikeBtn(view: View) {
        view.findViewById<ImageView>(R.id.album_view_like).setOnClickListener {
            if (this.album?.favorite === "true") {
                this.album?.favorite = "false"
            } else {
                this.album?.favorite = "true"
            }

            when (album?.favorite) {
                "true" -> {
                    albumViewModel.updateAlbum(this.album!!)
                    view.findViewById<ImageView>(R.id.album_view_heart).setImageResource(R.drawable.like_on)
                }
                "false" -> {
                    albumViewModel.updateAlbum(this.album!!)
                    view.findViewById<ImageView>(R.id.album_view_heart).setImageResource(R.drawable.like_off)
                }
            }
        }
    }

    private fun setUpBackBtn(view: View) {
        view.findViewById<ImageView>(R.id.album_view_back).setOnClickListener {
            Navigation.findNavController(view).navigateUp()
        }
    }

    private fun setUpRecyclerView(view: View) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                tracksViewModel.getTopFiftyTracks()
            }
        }
        tracksViewModel.tracks.observe(viewLifecycleOwner) {
            view.findViewById<RecyclerView>(R.id.album_view_albums).apply {
                adapter = TracksRecyclerViewAdapter(it, "AlbumViewFragment")
                layoutManager = LinearLayoutManager(activity)
            }
        }
        tracksViewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        tracksViewModel.loading.observe(viewLifecycleOwner) {
//            if (it)
//                loadingProgress.visibility = View.VISIBLE
//            else
//                loadingProgress.visibility = View.INVISIBLE
        }

    }
}