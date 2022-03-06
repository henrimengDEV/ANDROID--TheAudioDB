package com.example.android__theaudiodb.infrastructure.sqlite.dao

import androidx.room.*
import com.example.android__theaudiodb.domain.album.Album

@Dao
interface AlbumDAO {

    @Query("SELECT * FROM Album")
    fun getAll(): List<Album>

    @Query("SELECT * FROM Album WHERE id = :artistId")
    fun getById(artistId: Long): Album

    @Query("SELECT * FROM Album WHERE name = :artistName")
    fun getByName(artistName: String): Album

    @Query("SELECT * FROM Album WHERE favorite = 'true'")
    fun getFavorites(): List<Album>

    @Update
    fun updateAlbum(album: Album): Void

    @Insert
    fun insertAlbum(album: Album)
    //    suspend fun insertArtist(artist: Artist)

    @Delete
    fun deleteAlbum(album: Album): Void
}