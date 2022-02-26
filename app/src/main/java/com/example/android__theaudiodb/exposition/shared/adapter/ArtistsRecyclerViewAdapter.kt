package com.example.android__theaudiodb.exposition.shared.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.android__theaudiodb.databinding.FragmentArtistBinding

import com.example.android__theaudiodb.domain.artist.Artist

class ArtistsRecyclerViewAdapter(
    private val values: List<Artist>
) : RecyclerView.Adapter<ArtistsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentArtistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
//        holder.artistItemImg.setImageURI(item.photo)
//        holder.artistItemNext.setOnClickListener {  }
        holder.artistItemName.text = item.strArtist
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentArtistBinding) : RecyclerView.ViewHolder(binding.root) {
        val artistItemImg: ImageView = binding.artistItemImg
        val artistItemName: TextView = binding.artistItemName
        val artistItemNext: TextView = binding.artistItemNext
    }

}