package com.example.android__theaudiodb.exposition.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.databinding.FragmentArtistBinding
import com.example.android__theaudiodb.databinding.FragmentMusicTitleBinding
import com.example.android__theaudiodb.domain.Track
import com.example.android__theaudiodb.domain.artist.Artist
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class TracksRecyclerViewAdapter(
    private val values: List<Track>,
    private val sourceDestination: String
) : RecyclerView.Adapter<TracksRecyclerViewAdapter.ViewHolder>() {

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
        holder.trackItemPos.text = (position.inc()).toString()
        Picasso.get()
            .load(item.trackImageURL)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .networkPolicy(NetworkPolicy.NO_CACHE)
            .error(R.drawable.ic_no_image)
            .noFade()
            .into(holder.trackItemImg)
        holder.trackItemName.setOnClickListener {
            val bundle = bundleOf("sourceDestination" to sourceDestination)
            when(sourceDestination) {
                "SearchingFragment" -> Navigation.findNavController(it).navigate(R.id.action_searchingFragment_to_artistFragment, bundle)
                "FavoritesFragment" -> Navigation.findNavController(it).navigate(R.id.action_favoritesFragment_to_artistFragment, bundle)
            }
        }
        holder.trackItemName.text = item.name
        holder.trackItemDesc.text = item.artist
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentMusicTitleBinding) : RecyclerView.ViewHolder(binding.root) {
        val trackItemPos: TextView = binding.itemPosition
        val trackItemImg: ImageView = binding.itemImage
        val trackItemName: TextView = binding.itemNumber
        val trackItemDesc: TextView = binding.content
    }

}