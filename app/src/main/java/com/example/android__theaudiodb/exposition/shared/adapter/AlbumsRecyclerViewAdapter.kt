package com.example.android__theaudiodb.exposition.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.databinding.FragmentAlbumBinding
import com.example.android__theaudiodb.domain.album.Album

class AlbumsRecyclerViewAdapter(
    private val values: List<Album>,
    private val sourceDestination: String
) : RecyclerView.Adapter<AlbumsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.albumItemImg.setImageResource(R.drawable.artist)
        holder.itemView.setOnClickListener {
            val bundle = bundleOf("sourceDestination" to sourceDestination)
            when(sourceDestination) {
                "SearchingFragment" -> Navigation.findNavController(it).navigate(R.id.action_searchingFragment_to_albumViewFragment, bundle)
                "FavoritesFragment" -> Navigation.findNavController(it).navigate(R.id.action_favoritesFragment_to_albumViewFragment, bundle)
                "RankingTabAlbumsFragment" -> Navigation.findNavController(it).navigate(R.id.action_itemFragment_to_albumViewFragment, bundle)
            }
        }
        holder.albumItemTitle.text = item.name
        holder.albumItemArtistName.text = item.name
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
        val albumItemImg: ImageView = binding.albumItemImg
        val albumItemTitle: TextView = binding.albumItemTitle
        val albumItemArtistName: TextView = binding.albumItemArtistName
        val albumItemNext: ImageView = binding.albumItemNext
    }

}