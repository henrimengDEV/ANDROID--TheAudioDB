package com.example.android__theaudiodb.infrastructure.SQLite.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArtistEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val genre: String,
    @ColumnInfo(name = "biography_en")
    val biographyEN: String,
    @ColumnInfo(name = "biography_fr")
    val biographyFR: String,
    val country: String,
    @ColumnInfo(name = "artist_logo_url")
    val artistLogoURL: String
)