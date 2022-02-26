package com.example.android__theaudiodb.domain

import com.example.android__theaudiodb.domain.artist.Artists
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("v1/json/2/search.php")
    fun retrieveArtist(@Query("s") artistName: String): Call<Artists>
}
