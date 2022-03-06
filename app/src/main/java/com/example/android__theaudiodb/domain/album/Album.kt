package com.example.android__theaudiodb.domain.album

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
class Album(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "genre") val genre: String? = null,
    @ColumnInfo(name = "biography_en") val descriptionEN: String? = null,
    @ColumnInfo(name = "biography_fr") val descriptionFR: String? = null,
    @ColumnInfo(name = "photo_url") val photoURL: String? = null,
    @ColumnInfo(name = "artist_name") val artistName: String? = null
) : Parcelable {
    override fun toString(): String {
        return "Album(id=$id, name=$name, genre=$genre, descriptionEN=$descriptionEN, descriptionFR=$descriptionFR, photoURL=$photoURL, artistName=$artistName)"
    }
}

