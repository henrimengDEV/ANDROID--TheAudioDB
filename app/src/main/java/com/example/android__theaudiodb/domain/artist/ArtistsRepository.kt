package com.example.android__theaudiodb.domain.artist

import retrofit2.Callback

interface ArtistsRepository {
    fun getByName(name: String): Artist?
    fun getAll(): List<Artist>
}