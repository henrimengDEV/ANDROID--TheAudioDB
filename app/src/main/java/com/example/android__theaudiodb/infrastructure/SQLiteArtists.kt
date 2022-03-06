package com.example.android__theaudiodb.infrastructure

import com.example.android__theaudiodb.domain.artist.Artist
import com.example.android__theaudiodb.domain.artist.Artists
import com.example.android__theaudiodb.infrastructure.sqlite.dao.ArtistDAO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SQLiteArtists @Inject constructor(private val artistDAO: ArtistDAO) : Artists {

    override fun getByName(name: String): Artist {
        return this.artistDAO.getByName(name)
    }

    override fun getAll(): List<Artist> {
        return this.artistDAO.getAll()
    }

    override fun add() {
        TODO("Not yet implemented")
    }
}