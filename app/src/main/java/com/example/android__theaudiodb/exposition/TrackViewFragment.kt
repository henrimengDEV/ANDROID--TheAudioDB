package com.example.android__theaudiodb.exposition.track

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.exposition.shared.FileUtils.Companion.hideMenu
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackViewFragment : Fragment(R.layout.fragment_track_view) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        hideMenu(view)
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