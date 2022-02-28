package com.example.android__theaudiodb.exposition.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.databinding.FragmentMusicTitleBinding
import com.example.android__theaudiodb.domain.artist.Artist
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

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
        holder.idView.text = item.strArtist
        holder.contentView.text = item.strCountry
        Picasso.get()
            .load(item.strArtistLogo)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .networkPolicy(NetworkPolicy.NO_CACHE)
            .error(R.drawable.ic_no_image)
            .noFade()
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = values.size
    fun values(it: Artist?) {

    }

    inner class ViewHolder(binding: FragmentMusicTitleBinding) : RecyclerView.ViewHolder(binding.root) {
        val posView: TextView = binding.itemPosition
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content
        val imageView: ImageView = binding.itemImage

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }



}