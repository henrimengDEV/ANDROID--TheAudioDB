package com.example.android__theaudiodb.domain.artist

interface ArtistsRepository {
    fun getByName(name: String): Artist?
    fun getAll(): List<Artist>
}