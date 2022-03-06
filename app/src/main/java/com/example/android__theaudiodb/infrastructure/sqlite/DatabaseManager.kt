//package com.example.android__theaudiodb.infrastructure.SQLite
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.example.android__theaudiodb.infrastructure.SQLite.entity.ArtistEntity
//import com.example.android__theaudiodb.infrastructure.dao.ArtistDAO
//
//@Database(entities = [ArtistEntity::class], version = 1)
//abstract class DatabaseManager : RoomDatabase() {
//    abstract fun dao(): ArtistDAO?
//
//    companion object {
//        // Singleton
//        private var sInstance: DatabaseManager? = null
//        fun getDatabase(context: Context): DatabaseManager? {
//            if (sInstance == null) {
//                sInstance = Room.databaseBuilder(
//                    context.applicationContext,
//                    DatabaseManager::class.java, "db.sqlite"
//                ).build()
//            }
//            return sInstance
//        }
//    }
//}