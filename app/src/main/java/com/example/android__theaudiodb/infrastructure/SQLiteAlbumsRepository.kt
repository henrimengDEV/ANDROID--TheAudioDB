package com.example.android__theaudiodb.infrastructure

import com.example.android__theaudiodb.domain.album.Album
import com.example.android__theaudiodb.infrastructure.sqlite.dao.AlbumDAO
import javax.inject.Inject
import javax.inject.Singleton

//TODO implement ArtistRepo mais faut gérer ArtistEntity -> Artist

@Singleton
class SQLiteAlbumsRepository @Inject constructor(private val albumDAO: AlbumDAO) {

    fun getByName(name: String): Album {
        return this.albumDAO.getByName(name)
    }

    fun getAll(): List<Album> {
        return this.albumDAO.getAll()
    }
}