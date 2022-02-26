package com.example.android__theaudiodb.exposition.ranking

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.domain.artist.Artists
import com.example.android__theaudiodb.exposition.shared.adapter.MusicTitlesRecyclerViewAdapter
import com.example.android__theaudiodb.infrastructure.APIRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RankingTabMusicTitlesFragment : Fragment(R.layout.fragment_ranking_title) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerView(view)
    }

    fun setUpRecyclerView(view: View) {
        val loadingProgress = view.findViewById<ProgressBar>(R.id.indeterminateBar)
        loadingProgress.visibility = View.VISIBLE
        APIRepository.retrieveArtist("coldplay", object: Callback<Artists> {
            override fun onFailure(call: Call<Artists>, t: Throwable)
            {
                println("ERROR with api request")
                throw t
            }

            override fun onResponse(call: Call<Artists>, response: Response<Artists>) {
                println("Code ${response.code()}, Artist = ${response.body()}")
                println("Artist = ${response.body()?.artists}")
                view.findViewById<RecyclerView>(R.id.music_titles).apply {
                    adapter = MusicTitlesRecyclerViewAdapter(response.body()?.artists!!)
                    layoutManager = LinearLayoutManager(activity)
                }
                loadingProgress.visibility = View.INVISIBLE
            }

        })

    }
}