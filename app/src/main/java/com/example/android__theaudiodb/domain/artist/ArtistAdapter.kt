package com.example.android__theaudiodb.domain.artist

import com.example.android__theaudiodb.application.dto.ArtistDTO

class ArtistAdapter {
    companion object{
        fun adapt(source: ArtistDTO): Artist {
            return Artist(
                source.idArtist,
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