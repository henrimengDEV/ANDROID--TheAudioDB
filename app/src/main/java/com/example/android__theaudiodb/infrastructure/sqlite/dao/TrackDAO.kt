package com.example.android__theaudiodb.infrastructure.sqlite.dao

import androidx.room.*
import com.example.android__theaudiodb.domain.track.Track

//TODO simple copier coller de merde

@Dao
interface TrackDAO {

    @Query("SELECT * FROM Track")
    fun getAll(): List<Track>

    @Query("SELECT * FROM Track WHERE id = :artistId")
    fun getById(artistId: Int): Track

    @Query("SELECT * FROM Track WHERE name = :artistName")
    fun getByName(artistName: String): Track

    @Update
    fun updateTrack(track: Track): Void

    @Insert
    fun insertTrack(track: Track)
    //    suspend fun insertArtist(artist: Artist)

    @Delete
    fun deleteTrack(track: Track): Void
}