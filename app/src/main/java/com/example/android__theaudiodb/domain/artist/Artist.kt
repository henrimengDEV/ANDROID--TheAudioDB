package com.example.android__theaudiodb.domain.artist

class Artist (
   val strArtist       : String? = null,
   val strGenre      : String? = null,
   val strBiographyEN: String? = null,
   val strBiographyFR: String? = null,
   val strCountry    : String? = null,
   val strArtistLogo      : String? = null,

) {
   override fun toString(): String {
      return "Artist(" +
              "strArtist=$strArtist, " +
              "strGenre=$strGenre, " +
              "strBiographyEN=$strBiographyEN, " +
              "strBiographyFR=$strBiographyFR, " +
              "strCountry=$strCountry, " +
              "strArtistLogo=$strArtistLogo" +
              ")"
   }
}
