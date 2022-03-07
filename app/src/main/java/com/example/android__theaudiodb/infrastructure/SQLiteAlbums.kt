package com.example.android__theaudiodb.infrastructure

import com.example.android__theaudiodb.domain.album.Album
import com.example.android__theaudiodb.domain.album.Albums
import com.example.android__theaudiodb.infrastructure.sqlite.dao.AlbumDAO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SQLiteAlbums @Inject constructor(private val albumDAO: AlbumDAO) {

    fun getByName(name: String): Album {
        return this.albumDAO.getByName(name)
    }

    fun getAll(): List<Album> {
        return this.albumDAO.getAll()
    }

    fun getFavorites(): List<Album> {
        return this.albumDAO.getFavorites();
    }

    fun add(album: Album): Album {
        var albumInBase: Album = this.albumDAO.getById(albumId = album.id)
        val isPresent: Boolean = albumInBase === null;

        if (isPresent) {
            println("add -> ${album.id} -> ${album.favorite}")
            this.albumDAO.insertAlbum(album)
        } else {
            if (album.favorite === null)
                album.favorite = albumInBase.favorite
            println("update -> ${album.id} -> ${album.favorite}")

            this.albumDAO.updateAlbum(album)
        }

        albumInBase = this.albumDAO.getById(albumId = album.id)
        return albumInBase
    }
}