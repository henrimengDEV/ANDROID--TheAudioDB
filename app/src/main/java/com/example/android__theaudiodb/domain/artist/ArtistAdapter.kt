package com.example.android__theaudiodb.domain.artist

import ArtistDTO

class ArtistAdapter {
    companion object{
        fun adapt(source: ArtistDTO): Artist {
            return Artist(
                source.strArtist,
                source.strGenre,
                source.strBiographyEN,
                source.strBiographyFR,
                source.strCountry,
                source.strArtistLogo
            )
        }
    }
}