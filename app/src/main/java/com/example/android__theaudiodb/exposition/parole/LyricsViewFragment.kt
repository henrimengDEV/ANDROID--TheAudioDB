package com.example.android__theaudiodb.exposition.parole

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.android__theaudiodb.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LyricsViewFragment : Fragment(R.layout.fragment_lyrics_view) {
    private var sourceDestination: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.sourceDestination = arguments?.get("sourceDestination") as String
        setUpBackBtn(view, sourceDestination!!)
        view.findViewById<ImageView>(R.id.lyrics_view_miniature).setImageResource(R.drawable.artist) //miniature Parole
        view.findViewById<TextView>(R.id.lyrics_view_track).text = "Titre" // Musique parole
        view.findViewById<TextView>(R.id.lyrics_view_artist).text = "Artist" // Artiste parole
        view.findViewById<TextView>(R.id.lyrics).text = "PAROLES" // Parole de la musique
    }

    //Back Button
    private fun setUpBackBtn(view: View, sourceDestination: String) {
        view.findViewById<ImageView>(R.id.lyrics_view_back).setOnClickListener {
//            FileUtils.showMenu(view)
            when(sourceDestination) {
                "RankingTabMusicTitlesFragment" -> Navigation.findNavController(it).navigate(R.id.action_lyricsViewFragment_to_itemFragment)
                "ArtistViewFragment" -> Navigation.findNavController(it).navigate(R.id.action_lyricsViewFragment_to_artistFragment)
                "AlbumViewFragment" -> Navigation.findNavController(it).navigate(R.id.action_lyricsViewFragment_to_albumViewFragment2)
            }
        }
    }

}