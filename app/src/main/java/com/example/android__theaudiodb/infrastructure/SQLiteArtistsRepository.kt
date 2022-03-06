package com.example.android__theaudiodb.infrastructure

import com.example.android__theaudiodb.domain.artist.Artist
import com.example.android__theaudiodb.infrastructure.sqlite.dao.ArtistDAO
import javax.inject.Inject
import javax.inject.Singleton

//TODO implement ArtistRepo mais faut gérer ArtistEntity -> Artist

@Singleton
class SQLiteArtistsRepository @Inject constructor(private val artistDAO: ArtistDAO) {

    fun getByName(name: String): Artist {
        return this.artistDAO.getByName(name)
    }

    fun getAll(): List<Artist> {
        return this.artistDAO.getAll()
    }
}