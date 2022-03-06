package com.example.android__theaudiodb.domain.artist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Artist(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "genre") val genre: String? = null,
    @ColumnInfo(name = "biography_en") val biographyEN: String? = null,
    @ColumnInfo(name = "biography_fr") val biographyFR: String? = null,
    @ColumnInfo(name = "country") val country: String? = null,
    @ColumnInfo(name = "artist_logo_url") val artistLogoURL: String? = null,
)
