package com.example.android__theaudiodb.infrastructure.sqlite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.android__theaudiodb.domain.album.Album
import com.example.android__theaudiodb.domain.artist.Artist
import com.example.android__theaudiodb.domain.track.Track
import com.example.android__theaudiodb.infrastructure.sqlite.dao.AlbumDAO
import com.example.android__theaudiodb.infrastructure.sqlite.dao.ArtistDAO
import com.example.android__theaudiodb.infrastructure.sqlite.dao.TrackDAO

@Database(entities = [Artist::class, Album::class, Track::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun artistDAO(): ArtistDAO
    abstract fun albumDAO(): AlbumDAO
    abstract fun trackDAO(): TrackDAO

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "the_audio_db")
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            request()
                        }
                    }
                )
                .build()
        }

        private fun request() {
            // val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
            // .setInputData(workDataOf(KEY_FILENAME to PLANT_DATA_FILENAME))
            // .build()
            // WorkManager.getInstance(context).enqueue(request)
        }
    }
}