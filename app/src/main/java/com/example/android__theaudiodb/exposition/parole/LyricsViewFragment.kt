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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpBackBtn(view)
        view.findViewById<ImageView>(R.id.lyrics_view_miniature).setImageResource(R.drawable.artist) //miniature Parole
        view.findViewById<TextView>(R.id.lyrics_view_track).text = "Titre" // Musique parole
        view.findViewById<TextView>(R.id.lyrics_view_artist).text = "Artist" // Artiste parole
        view.findViewById<TextView>(R.id.lyrics).text = "PAROLES" // Parole de la musique
    }

    private fun setUpBackBtn(view: View) {
        view.findViewById<ImageView>(R.id.lyrics_view_back).setOnClickListener {
            Navigation.findNavController(view).navigateUp()
        }
    }

}