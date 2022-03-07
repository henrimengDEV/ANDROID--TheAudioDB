package com.example.android__theaudiodb.infrastructure

import com.example.android__theaudiodb.domain.api.APIService

class APIRepository {

    suspend fun getArtist(artistName: String) = APIService.getInstance().retrieveArtist(artistName)

    suspend fun getTopFiftyTracksOfAllTime() = APIService.getInstance().retrieveTopFiftyMusicOfAllTime()

    suspend fun getTopTenAlbumsOfAllTime() = APIService.getInstance().retrieveTopTenAlbumsOfAllTime()

    suspend fun getAllAlbumsByArtistName(artistName: String) = APIService.getInstance().retrieveAllAlbumsByArtistName(artistName)

    suspend fun getAllTracksFromAlbumId(albumId: Long) = APIService.getInstance().retrieveAllTracksFromAlbumId(albumId)

}