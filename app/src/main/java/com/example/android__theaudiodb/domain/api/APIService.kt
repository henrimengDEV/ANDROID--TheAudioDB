package com.example.android__theaudiodb.domain.api

import com.example.android__theaudiodb.application.AlbumsDTO
import com.example.android__theaudiodb.application.ArtistsDTO
import com.example.android__theaudiodb.application.LovedAlbumsDTO
import com.example.android__theaudiodb.application.LovedTracksDTO
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    companion object {
        private var apiService: APIService? = null

        fun getInstance() : APIService {
            if (apiService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://www.theaudiodb.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                apiService = retrofit.create(APIService::class.java)
            }
            return apiService!!
        }
    }

    @GET("v1/json/523532/search.php")
    suspend fun retrieveArtist(@Query("s") artistName: String): Response<ArtistsDTO>

    @GET("v1/json/523532/searchalbum.php")
    suspend fun retrieveAllAlbumsByArtistName(@Query("s") artistName: String): Response<AlbumsDTO>

    @GET("v1/json/523532/mostloved.php?format=track")
    suspend fun retrieveTopFiftyMusicOfAllTime(): Response<LovedTracksDTO>

    @GET("v1/json/523532/mostloved.php?format=album")
    suspend fun retrieveTopTenAlbumsOfAllTime(): Response<LovedAlbumsDTO>



    @GET("discography.php")
    fun retrieveDiscography(@Query("s") artistName: String): Call<ArtistsDTO>


}
