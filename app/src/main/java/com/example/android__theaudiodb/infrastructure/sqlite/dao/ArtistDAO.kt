package com.example.android__theaudiodb.infrastructure.sqlite.dao

import androidx.room.*
import com.example.android__theaudiodb.domain.album.Album
import com.example.android__theaudiodb.domain.artist.Artist

@Dao
interface ArtistDAO {

    @Query("SELECT * FROM Artist")
    fun getAll(): List<Artist>

    @Query("SELECT * FROM Artist WHERE id = :artistId")
    fun getById(artistId: Long): Artist

    @Query("SELECT * FROM Artist WHERE name = :artistName")
    fun getByName(artistName: String): Artist

    @Query("SELECT * FROM Artist WHERE favorite = 1 ")
    fun getFavorites(): List<Artist>

    @Update
    fun updateArtist(artist: Artist): Void

    @Insert
    fun insertArtist(artist: Artist)
    //    suspend fun insertArtist(artist: Artist)

    @Delete
    fun deleteArtist(artist: Artist): Void
}