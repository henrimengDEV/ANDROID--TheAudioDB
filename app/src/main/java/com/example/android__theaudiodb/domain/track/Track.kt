package com.example.android__theaudiodb.domain.track

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Track(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "id_album") val idAlbum: Int?,
    @ColumnInfo(name = "id_artist") val idArtist: Int?,
    @ColumnInfo(name = "id_lyric") val idLyric: Int?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "album") val album: String?,
    @ColumnInfo(name = "artist") val artist: String?,
    @ColumnInfo(name = "cd") val CD: String?,
    @ColumnInfo(name = "duration") val duration: Int?,
    @ColumnInfo(name = "genre") val genre: String?,
    @ColumnInfo(name = "mood") val mood: String?,
    @ColumnInfo(name = "style") val style: String?,
    @ColumnInfo(name = "strTheme") val strTheme: String?,
    @ColumnInfo(name = "description_en") val descriptionEN: String?,
    @ColumnInfo(name = "description_fr") val descriptionFR: String?,
    @ColumnInfo(name = "loved") val loved: Int?,
    @ColumnInfo(name = "score") val score: Double?,
    @ColumnInfo(name = "score_votes") val scoreVotes: Int?,
    @ColumnInfo(name = "total_listeners") val totalListeners: Int?,
    @ColumnInfo(name = "total_plays") val totalPlays: Int?,
    @ColumnInfo(name = "track_image_url") val trackImageURL: String?
)
