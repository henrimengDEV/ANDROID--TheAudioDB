package com.example.android__theaudiodb.infrastructure

import com.example.android__theaudiodb.domain.APIService

class APIRepository {

    suspend fun getArtist(artistName: String) = APIService.getInstance().retrieveArtist(artistName)

    suspend fun getTopFiftyTracksOfAllTime() = APIService.getInstance().retrieveTopFiftyMusicOfAllTime()

    suspend fun getTopTenAlbumsOfAllTime() = APIService.getInstance().retrieveTopTenAlbumsOfAllTime()

}