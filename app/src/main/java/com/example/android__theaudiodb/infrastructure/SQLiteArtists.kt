package com.example.android__theaudiodb.infrastructure

import com.example.android__theaudiodb.domain.album.Album
import com.example.android__theaudiodb.domain.artist.Artist
import com.example.android__theaudiodb.domain.artist.Artists
import com.example.android__theaudiodb.infrastructure.sqlite.dao.ArtistDAO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SQLiteArtists @Inject constructor(private val artistDAO: ArtistDAO) {

    fun getByName(name: String): Artist {
        return this.artistDAO.getByName(name)
    }

    fun getAll(): List<Artist> {
        return this.artistDAO.getAll()
    }

    fun getFavorites(): List<Artist> {
        return this.artistDAO.getFavorites();
    }

    fun add(artist: Artist): Artist {
        var artistInBase: Artist = this.artistDAO.getById(artistId = artist.id)
        val isPresent: Boolean = artistInBase === null

        if (isPresent) {
            print("add -> ${artist.id} -> ${artist.favorite}")
            this.artistDAO.insertArtist(artist)
        } else {
            if (artist.favorite === null)
                artist.favorite = artistInBase.favorite
            println("update -> ${artist.id} -> ${artist.favorite}")

            this.artistDAO.updateArtist(artist)
        }
        artistInBase = this.artistDAO.getById(artistId = artist.id)
        return artistInBase
    }
}