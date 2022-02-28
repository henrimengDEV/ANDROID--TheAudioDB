package com.example.android__theaudiodb.domain.artist

class Artist (
   val name       : String? = null,
   val genre      : String? = null,
   val biographyEN: String? = null,
   val biographyFR: String? = null,
   val country    : String? = null,
   val artistLogoURL      : String? = null,

   ) {
   override fun toString(): String {
      return "Artist(" +
              "strArtist=$name, " +
              "strGenre=$genre, " +
              "strBiographyEN=$biographyEN, " +
              "strBiographyFR=$biographyFR, " +
              "strCountry=$country, " +
              "strArtistLogo=$artistLogoURL" +
              ")"
   }
}
