package com.example.android__theaudiodb.infrastructure

import com.example.android__theaudiodb.domain.track.Track
import com.example.android__theaudiodb.infrastructure.sqlite.dao.TrackDAO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SQLiteTracks @Inject constructor(private val trackDAO: TrackDAO) {

    fun getByName(name: String): Track {
        return this.trackDAO.getByName(name)
    }

    fun getAll(): List<Track> {
        return this.trackDAO.getAll()
    }

    fun getAllByArtistId(id: Long): List<Track> {
        return this.trackDAO.getAllByArtistId(id)
    }

    fun getAllByAlbumId(id: Long): List<Track> {
        return this.trackDAO.getAllByAlbumId(id)
    }
}