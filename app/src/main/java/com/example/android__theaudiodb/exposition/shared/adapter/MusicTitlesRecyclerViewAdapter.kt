package com.example.android__theaudiodb.exposition.shared.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.example.android__theaudiodb.R

import com.example.android__theaudiodb.databinding.FragmentMusicTitleBinding
import com.example.android__theaudiodb.databinding.FragmentMusicTitleV2Binding
import com.example.android__theaudiodb.domain.artist.Artist

class MusicTitlesRecyclerViewAdapter(
    private val values: List<Artist>,
    private val sourceDestination: String
) : RecyclerView.Adapter<MusicTitlesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (this.sourceDestination === "AlbumViewFragment" || this.sourceDestination === "ArtistViewFragment") {
            ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_music_title_v2, parent, false), "AlbumViewFragment"
            )
        } else {
            ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_music_title, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.currentItem.setOnClickListener {
            val bundle = bundleOf("sourceDestination" to sourceDestination)
            when(sourceDestination) {
                "RankingTabMusicTitlesFragment" -> Navigation.findNavController(it).navigate(R.id.action_itemFragment_to_lyricsViewFragment, bundle)
                "ArtistViewFragment" -> Navigation.findNavController(it).navigate(R.id.action_artistFragment_to_lyricsViewFragment, bundle)
                "AlbumViewFragment" -> Navigation.findNavController(it).navigate(R.id.action_albumViewFragment_to_lyricsViewFragment, bundle)
            }
        }

        when (this.sourceDestination) {
            "AlbumViewFragment", "ArtistViewFragment" -> {
                holder.currentItem.findViewById<TextView>(R.id.item_position).text = (position + 1).toString()
                holder.currentItem.findViewById<TextView>(R.id.item_title).text = item.name
            }
            else -> {
                holder.currentItem.findViewById<TextView>(R.id.item_position).text = (position + 1).toString()
                holder.currentItem.findViewById<TextView>(R.id.item_number).text = item.name
                holder.currentItem.findViewById<TextView>(R.id.content).text = item.country
            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View, sourceDestination: String? = null) : RecyclerView.ViewHolder(view) {
        var currentItem: LinearLayout = when (sourceDestination) {
            "AlbumViewFragment", "ArtistViewFragment" -> {
                view.findViewById(R.id.music_title_v2)
            }
            else -> {
                view.findViewById(R.id.music_title)
            }
        }
    }

}