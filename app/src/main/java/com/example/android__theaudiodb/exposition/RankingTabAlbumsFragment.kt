package com.example.android__theaudiodb.exposition

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
import com.example.android__theaudiodb.exposition.viewmodel.AlbumsViewModel
import com.example.android__theaudiodb.exposition.adapter.AlbumsRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RankingTabAlbumsFragment : Fragment(R.layout.fragment_ranking_album) {

    private val viewModel: AlbumsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerView(view)
    }

    fun setUpRecyclerView(view: View) {
        val loadingProgress = view.findViewById<ProgressBar>(R.id.indeterminateBar)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getTopFiftyTracks()
            }
        }
        viewModel.albums.observe(viewLifecycleOwner) {
            view.findViewById<RecyclerView>(R.id.albums).apply {
                adapter = AlbumsRecyclerViewAdapter(it, "RankingTabAlbumsFragment")
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
}