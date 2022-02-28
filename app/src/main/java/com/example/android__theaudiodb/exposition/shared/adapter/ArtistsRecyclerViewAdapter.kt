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
import com.example.android__theaudiodb.domain.artist.Artist
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class ArtistsRecyclerViewAdapter(
    private val values: List<Artist>,
    private val sourceDestination: String
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
        Picasso.get()
            .load(item.strArtistLogo)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .networkPolicy(NetworkPolicy.NO_CACHE)
            .error(R.drawable.ic_no_image)
            .noFade()
            .into(holder.artistItemImg)
        holder.artistItemNext.setOnClickListener {
            val bundle = bundleOf("sourceDestination" to sourceDestination)
            when(sourceDestination) {
                "SearchingFragment" -> Navigation.findNavController(it).navigate(R.id.action_searchingFragment_to_artistFragment, bundle)
                "FavoritesFragment" -> Navigation.findNavController(it).navigate(R.id.action_favoritesFragment_to_artistFragment, bundle)
            }

        }
        holder.artistItemName.text = item.strArtist
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentArtistBinding) : RecyclerView.ViewHolder(binding.root) {
        val artistItemImg: ImageView = binding.artistItemImg
        val artistItemName: TextView = binding.artistItemName
        val artistItemNext: TextView = binding.artistItemNext
    }

}