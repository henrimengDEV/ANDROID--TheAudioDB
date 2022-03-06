package com.example.android__theaudiodb.infrastructure

import com.example.android__theaudiodb.domain.artist.Artist
import com.example.android__theaudiodb.domain.artist.Artists

@Deprecated("")
object InMemoryArtists : Artists {

    private val artists: List<Artist> = listOf(
        Artist(id = 1, name = "Coldplay", country = "France"),
        Artist(id = 2, name = "foo", country = "France"),
    )

    override fun getByName(name: String): Artist? {
        return this.artists.find { artist: Artist -> artist.name === name }
    }

    override fun getAll(): List<Artist> {
        return this.artists
    }

    override fun add() {
        TODO("Not yet implemented")
    }

}