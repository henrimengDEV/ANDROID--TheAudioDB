package com.example.android__theaudiodb.infrastructure.sqlite.dao

import androidx.room.*
import com.example.android__theaudiodb.domain.track.Track

@Dao
interface TrackDAO {

    @Query("SELECT * FROM Track")
    fun getAll(): List<Track>

    @Query("SELECT * FROM Track WHERE id = :id")
    fun getById(id: Long): Track

    @Query("SELECT * FROM Track WHERE name = :trackName")
    fun getByName(trackName: String): Track

    @Query("SELECT * FROM Track WHERE id_artist = :artistId")
    fun getAllByArtistId(artistId: Long): List<Track>

    @Query("SELECT * FROM Track WHERE id_album = :albumId")
    fun getAllByAlbumId(albumId: Long): List<Track>

    @Update
    fun updateTrack(track: Track): Void

    @Insert
    fun insertTrack(track: Track)
    //    suspend fun insertArtist(artist: Artist)

    @Delete
    fun deleteTrack(track: Track): Void
}