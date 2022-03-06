package com.example.android__theaudiodb.infrastructure

import com.example.android__theaudiodb.domain.album.Album
import com.example.android__theaudiodb.domain.album.Albums

@Deprecated("")
object InMemoryAlbums : Albums {

    private val albums: List<Album> = listOf(
        Album(id = 1, name = "Coldplay", descriptionFR = "France"),
        Album(id = 2, name = "foo", descriptionFR = "France"),
    )

    override fun getByName(name: String): Album? {
        return this.albums.find { album: Album -> album.name === name }
    }

    override fun getAll(): List<Album> {
        return this.albums
    }

    override fun add(album: Album) {
        TODO("Not yet implemented")
    }

}