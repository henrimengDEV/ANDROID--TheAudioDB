package com.example.android__theaudiodb.exposition

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.android__theaudiodb.R
import com.example.android__theaudiodb.domain.track.Track
import com.example.android__theaudiodb.exposition.common.FileUtils.Companion.hideMenu
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackViewFragment : Fragment(R.layout.fragment_track_view) {

    private var track: Track? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        track = arguments?.get("track") as Track?
        println("pazodiazjpodjazpodpzaojdazjdopazd  " + track)
        hideMenu(view)
        setUpBackBtn(view)
        setUpView(view)
    }

    private fun setUpView(view: View) {
        Picasso.get()
            .load(this.track?.trackImageURL)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .networkPolicy(NetworkPolicy.NO_CACHE)
            .error(R.drawable.ic_no_image)
            .noFade()
            .into(view.findViewById<ImageView>(R.id.lyrics_view_img))
        view.findViewById<ImageView>(R.id.lyrics_view_img).setColorFilter(R.color.transparent_black)

        Picasso.get()
            .load(this.track?.trackImageURL)
            .memoryPolicy(MemoryPolicy.NO_CACHE)
            .networkPolicy(NetworkPolicy.NO_CACHE)
            .error(R.drawable.ic_no_image)
            .noFade()
            .into(view.findViewById<ImageView>(R.id.lyrics_view_miniature))

        view.findViewById<TextView>(R.id.lyrics_view_track).text = this.track?.name // Musique parole
        view.findViewById<TextView>(R.id.lyrics_view_artist).text = this.track?.artist // Artiste parole
        view.findViewById<TextView>(R.id.lyrics).text = this.track?.lyrics // Parole de la musique
    }

    private fun setUpBackBtn(view: View) {
        view.findViewById<ImageView>(R.id.lyrics_view_back).setOnClickListener {
            Navigation.findNavController(view).navigateUp()
        }
    }
}