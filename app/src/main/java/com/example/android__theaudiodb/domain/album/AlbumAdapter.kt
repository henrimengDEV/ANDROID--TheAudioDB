package com.example.android__theaudiodb.domain.album

import com.example.android__theaudiodb.application.AlbumDTO

class AlbumAdapter {
    companion object {
        fun adapt(source: AlbumDTO): Album {
            return Album(
                id = source.idAlbum,
                name = source.strAlbum,
                genre = source.strGenre,
                descriptionEN = source.strDescription,
                descriptionFR = source.strDescriptionFR,
                photoURL = source.strAlbumThumb,
                artistName = source.strArtist,
                likes = source.intLoved,
                score = source.intScore,
            )
        }
    }
}