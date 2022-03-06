package com.example.android__theaudiodb.domain.artist

interface Artists {
    fun getByName(name: String): Artist?
    fun getAll(): List<Artist>
    fun add()
}