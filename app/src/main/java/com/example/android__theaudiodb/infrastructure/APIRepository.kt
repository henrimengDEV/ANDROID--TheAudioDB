package com.example.android__theaudiodb.infrastructure

import com.example.android__theaudiodb.domain.APIService
import com.example.android__theaudiodb.domain.artist.Artist
import com.example.android__theaudiodb.domain.artist.Artists
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIRepository {

        private var apiService: APIService? = null

        init
        {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.theaudiodb.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            apiService = retrofit.create(APIService::class.java)
        }

    fun retrieveArtist(artistName: String, callback: Callback<Artists>)
    {
        val call = apiService?.retrieveArtist(artistName)
        call?.enqueue(callback)
    }

}