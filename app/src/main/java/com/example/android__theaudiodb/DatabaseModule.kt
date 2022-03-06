package com.example.android__theaudiodb

import android.content.Context
import com.example.android__theaudiodb.infrastructure.sqlite.dao.ArtistDAO
import com.example.android__theaudiodb.infrastructure.sqlite.AppDatabase
import com.example.android__theaudiodb.infrastructure.sqlite.dao.AlbumDAO
import com.example.android__theaudiodb.infrastructure.sqlite.dao.TrackDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideArtistDAO(appDatabase: AppDatabase): ArtistDAO {
        return appDatabase.artistDAO();
    }

    @Provides
    fun provideAlbumDAO(appDatabase: AppDatabase): AlbumDAO {
        return appDatabase.albumDAO();
    }

    @Provides
    fun provideTrackDAO(appDatabase: AppDatabase): TrackDAO {
        return appDatabase.trackDAO();
    }
}
