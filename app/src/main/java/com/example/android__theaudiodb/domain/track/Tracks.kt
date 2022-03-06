package com.example.android__theaudiodb.domain.track

import com.example.android__theaudiodb.domain.artist.Artist

interface Tracks {
    fun getByName(name: String): Artist?
    fun getAll(): List<Artist>
    fun add()
}