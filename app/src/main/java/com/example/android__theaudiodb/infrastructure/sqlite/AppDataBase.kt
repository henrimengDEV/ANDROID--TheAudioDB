package com.example.android__theaudiodb.infrastructure.sqlite

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android__theaudiodb.infrastructure.dao.ArtistDAO
import com.example.android__theaudiodb.infrastructure.sqlite.entity.ArtistEntity

@Database(entities = [ArtistEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun artistDAO(): ArtistDAO
}