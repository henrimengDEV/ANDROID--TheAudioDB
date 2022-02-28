package com.example.android__theaudiodb.exposition.ranking

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.application.MainViewModel
import com.example.android__theaudiodb.exposition.shared.adapter.ArtistsRecyclerViewAdapter
import kotlinx.coroutines.launch


class RankingTabMusicTitlesFragment : Fragment(R.layout.fragment_ranking_title) {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val loadingProgress = view.findViewById<ProgressBar>(R.id.indeterminateBar)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getArtist("coldplay")
            }
        }

        viewModel.artist.observe(viewLifecycleOwner) {
            view.findViewById<RecyclerView>(R.id.music_titles).apply {
                adapter = ArtistsRecyclerViewAdapter(it, "RankingTabMusicTitlesFragment")
                layoutManager = LinearLayoutManager(activity)
            }
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it)
                loadingProgress.visibility = View.VISIBLE
            else
                loadingProgress.visibility = View.INVISIBLE
        }

    }

    fun setUpRecyclerView(view: View) {

    }
}