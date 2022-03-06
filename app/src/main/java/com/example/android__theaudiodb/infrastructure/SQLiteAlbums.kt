package com.example.android__theaudiodb.infrastructure

import com.example.android__theaudiodb.domain.album.Album
import com.example.android__theaudiodb.domain.album.Albums
import com.example.android__theaudiodb.infrastructure.sqlite.dao.AlbumDAO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SQLiteAlbums @Inject constructor(private val albumDAO: AlbumDAO) : Albums {

    override fun getByName(name: String): Album {
        return this.albumDAO.getByName(name)
    }

    override fun getAll(): List<Album> {
        return this.albumDAO.getAll()
    }

    override fun add() {
        TODO("Not yet implemented")
    }
}