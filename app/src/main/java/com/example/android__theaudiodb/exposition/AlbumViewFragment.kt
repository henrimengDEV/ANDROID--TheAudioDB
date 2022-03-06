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
import com.example.android__theaudiodb.domain.album.Album
import com.example.android__theaudiodb.exposition.common.FileUtils.Companion.hideMenu
import com.example.android__theaudiodb.exposition.adapter.TracksRecyclerViewAdapter
import com.example.android__theaudiodb.exposition.viewmodel.TracksViewModel
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlbumViewFragment : Fragment(R.layout.fragment_album_view) {

    private var album: Album? = null
    private val viewModel: TracksViewModel by activityViewModels()
    private var like: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        album = arguments?.get("album") as Album?
        setUpRecyclerView(view)
        setUpBackBtn(view)
        setUpView(view)
        setUpLikeBtn(view)
        hideMenu(view)
    }

    private fun setUpView(view: View) {
        when(like){
            true -> view.findViewById<ImageView>(R.id.album_view_heart).setImageResource(R.drawable.like_on)
            false -> view.findViewById<ImageView>(R.id.album_view_heart).setImageResource(R.drawable.like_off)
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

//        if (this.album.votes != null)
//            view.findViewById<TextView>(R.id.album_view_votes).visibility = View.GONE
//        view.findViewById<TextView>(R.id.album_view_votes_number).text = this.album.
    }

    private fun setUpLikeBtn(view: View) {
        view.findViewById<ImageView>(R.id.album_view_like).setOnClickListener {
            this.like = !this.like

            when(like){
                true -> view.findViewById<ImageView>(R.id.album_view_heart).setImageResource(R.drawable.like_on)
                false -> view.findViewById<ImageView>(R.id.album_view_heart).setImageResource(R.drawable.like_off)
            }
        }

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