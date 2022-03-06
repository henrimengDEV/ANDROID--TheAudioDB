package com.example.android__theaudiodb.infrastructure.dao

import androidx.room.*
import com.example.android__theaudiodb.domain.artist.Artist
import com.example.android__theaudiodb.infrastructure.sqlite.entity.ArtistEntity

@Dao
interface ArtistDAO {

    @Query("SELECT * FROM ArtistEntity")
    fun getAll(): List<ArtistEntity>

    @Query("SELECT * FROM ArtistEntity WHERE id = :artistId")
    fun getById(artistId: Int): ArtistEntity

    @Update
    fun updateArtist(artist: ArtistEntity): Void

    @Insert
    fun insertArtist(artist: ArtistEntity)
    //    suspend fun insertArtist(artist: Artist)

    @Delete
    fun deleteArtist(artist: ArtistEntity): Void
}