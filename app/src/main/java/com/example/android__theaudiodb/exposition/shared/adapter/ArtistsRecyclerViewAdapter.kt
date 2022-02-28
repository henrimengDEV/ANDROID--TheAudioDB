package com.example.android__theaudiodb.exposition.shared.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.databinding.FragmentArtistBinding

import com.example.android__theaudiodb.domain.artist.Artist

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
//        holder.artistItemImg.setImageURI(item.photo)
        holder.artistItemNext.setOnClickListener {
            if(sourceDestination.equals("SearchingFragment")){
                Navigation.findNavController(it).navigate(R.id.action_searchingFragment_to_artistFragment)
            } else if(sourceDestination.equals("FavoritesFragment")) {
                Navigation.findNavController(it).navigate(R.id.action_favoritesFragment_to_artistFragment)
            }
        }
        holder.artistItemName.text = item.name
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentArtistBinding) : RecyclerView.ViewHolder(binding.root) {
        val artistItemImg: ImageView = binding.artistItemImg
        val artistItemName: TextView = binding.artistItemName
        val artistItemNext: TextView = binding.artistItemNext
    }

}