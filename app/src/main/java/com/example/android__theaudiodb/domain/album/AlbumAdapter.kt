package com.example.android__theaudiodb.domain.album

import com.example.android__theaudiodb.application.dto.AlbumDTO

class AlbumAdapter {
    companion object {
        fun adapt(source: AlbumDTO): Album {
            return Album(
                source.strAlbum,
                source.strGenre,
                source.strDescription,
                source.strDescriptionFR,
                source.strAlbumThumb,
                source.strArtist
            )
        }
    }
}