package com.example.android__theaudiodb.infrastructure

import com.example.android__theaudiodb.domain.album.Album
import com.example.android__theaudiodb.domain.album.Albums

object InMemoryAlbumsRepository : Albums {

    private val albums: List<Album> = listOf(
        Album(name = "Coldplay", descriptionFR = "France"),
        Album(name = "foo", descriptionFR = "France"),
    )

    override fun getByName(name: String): Album? {
        return this.albums.find { album: Album -> album.name === name }
    }

    override fun getAll(): List<Album> {
        return this.albums
    }

}