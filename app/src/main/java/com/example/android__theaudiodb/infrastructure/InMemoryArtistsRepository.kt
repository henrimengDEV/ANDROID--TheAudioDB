package com.example.android__theaudiodb.infrastructure

import com.example.android__theaudiodb.domain.artist.Artist
import com.example.android__theaudiodb.domain.artist.ArtistsRepository

object InMemoryArtistsRepository : ArtistsRepository {

    private val artists: List<Artist> = listOf(
        Artist(name = "Coldplay", country = "France"),
        Artist(name = "foo", country = "France"),
    )

    override fun getByName(name: String): Artist? {
        return this.artists.find { artist: Artist -> artist.name === name }
    }

    override fun getAll(): List<Artist> {
        return this.artists
    }

}