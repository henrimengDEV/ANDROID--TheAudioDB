package com.example.android__theaudiodb.domain.track

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
class Track(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "id_album") val idAlbum: Long?,
    @ColumnInfo(name = "id_artist") val idArtist: Long?,
    @ColumnInfo(name = "id_lyric") val idLyric: Long?,
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
    @ColumnInfo(name = "track_image_url") val trackImageURL: String?,
    @ColumnInfo(name = "lyrics") val lyrics: String?
) : Parcelable {
    override fun toString(): String {
        return "Track(id=$id, idAlbum=$idAlbum, idArtist=$idArtist, idLyric=$idLyric, name=$name, album=$album, artist=$artist, CD=$CD, duration=$duration, genre=$genre, mood=$mood, style=$style, strTheme=$strTheme, descriptionEN=$descriptionEN, descriptionFR=$descriptionFR, loved=$loved, score=$score, scoreVotes=$scoreVotes, totalListeners=$totalListeners, totalPlays=$totalPlays, trackImageURL=$trackImageURL, lyrics=$lyrics)"
    }
}
