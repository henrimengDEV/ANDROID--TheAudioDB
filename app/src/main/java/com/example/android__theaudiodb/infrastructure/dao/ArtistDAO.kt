package com.example.android__theaudiodb.infrastructure.dao

import androidx.room.*
import com.example.android__theaudiodb.domain.artist.Artist
import com.example.android__theaudiodb.infrastructure.SQLite.entity.ArtistEntity

@Dao
interface ArtistDAO {

    @Insert
    suspend fun insertArtist(artist: Artist)

    @Delete
    fun deleteArtist(artist: Artist): Void

    @Update
    fun updateArtist(artist: Artist): Void

    @Query("SELECT * FROM ArtistEntity")
    fun getAll(): List<ArtistEntity>

    @Query("SELECT * FROM ArtistEntity WHERE id = :artistId")
    fun getById(artistId: Int): ArtistEntity


}