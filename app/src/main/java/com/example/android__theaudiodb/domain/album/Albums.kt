package com.example.android__theaudiodb.domain.album

interface Albums {
    fun getByName(name: String): Album?
    fun getAll(): List<Album>
    fun add(album: Album)
}