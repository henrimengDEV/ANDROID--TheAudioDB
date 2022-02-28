package com.example.android__theaudiodb.exposition.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.databinding.FragmentMusicTitleBinding
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
        holder.posView.text = (position + 1).toString()
        holder.idView.text = item.name
        holder.contentView.text = item.country
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentMusicTitleBinding) : RecyclerView.ViewHolder(binding.root) {
        val posView: TextView = binding.itemPosition
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}