package com.example.android__theaudiodb.exposition.shared.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.databinding.ListItemTrackBinding
import com.example.android__theaudiodb.databinding.ListItemTrackV2Binding
import com.example.android__theaudiodb.domain.track.Track
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class TracksRecyclerViewAdapter(
    private val values: List<Track>,
    private val sourceDestination: String
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (sourceDestination) {
            "AlbumViewFragment" -> {
                ViewHolderV2(
                    ListItemTrackV2Binding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                ViewHolder(
                    ListItemTrackBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = values[position]

        when(holder) {
            is ViewHolder -> {
                holder.trackItemPos.text = (position.inc()).toString()
                Picasso.get()
                    .load(item.trackImageURL)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .error(R.drawable.ic_no_image)
                    .noFade()
                    .into(holder.trackItemImg)
                holder.trackItemName.text = item.name
                holder.trackItemDesc.text = item.artist
            }
            is ViewHolderV2 -> {
                holder.trackItemPos.text = position.toString()
                holder.itemTitle.text = item.name
            }
        }

        holder.itemView.setOnClickListener {
            val bundle = bundleOf("sourceDestination" to sourceDestination)
            when (sourceDestination) {
                "SearchingFragment" -> Navigation.findNavController(it)
                    .navigate(R.id.action_searchingFragment_to_artistFragment, bundle)
                "FavoritesFragment" -> Navigation.findNavController(it)
                    .navigate(R.id.action_favoritesFragment_to_artistFragment, bundle)
                "RankingTabAlbumsFragment", "RankingTabMusicTitlesFragment" -> Navigation.findNavController(it)
                    .navigate(R.id.action_rankingFragment_to_lyricsViewFragment, bundle)
                "AlbumViewFragment" -> Navigation.findNavController(it)
                    .navigate(R.id.action_albumViewFragment_to_lyricsViewFragment, bundle)
            }
        }
    }

    override fun getItemCount(): Int = values.size

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    inner class ViewHolder(binding: ListItemTrackBinding) : RecyclerView.ViewHolder(binding.root) {
        val trackItemPos: TextView = binding.itemPosition
        val trackItemImg: ImageView = binding.itemImage
        val trackItemName: TextView = binding.itemNumber
        val trackItemDesc: TextView = binding.content
    }

    inner class ViewHolderV2(binding: ListItemTrackV2Binding) : RecyclerView.ViewHolder(binding.root) {
        val trackItemPos: TextView = binding.itemPosition
        val itemTitle: TextView = binding.itemTitle
    }

}