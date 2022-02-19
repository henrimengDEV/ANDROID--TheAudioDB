package com.example.android__theaudiodb.exposition.shared.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.example.android__theaudiodb.databinding.FragmentMusicTitleBinding
import com.example.android__theaudiodb.domain.artist.Artist

class MusicTitlesRecyclerViewAdapter(
    private val values: List<Artist>
) : RecyclerView.Adapter<MusicTitlesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentMusicTitleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

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