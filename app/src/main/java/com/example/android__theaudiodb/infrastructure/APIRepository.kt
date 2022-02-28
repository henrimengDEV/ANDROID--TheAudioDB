package com.example.android__theaudiodb.infrastructure

import com.example.android__theaudiodb.domain.APIService

class APIRepository {

    suspend fun retrieveArtist(artistName: String) = APIService.getInstance().retrieveArtist(artistName)

}