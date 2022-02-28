package com.example.android__theaudiodb.application.dto



data class ArtistDTO (
    var idArtist           : String? = null,
    var strArtist          : String? = null,
    var strArtistStripped  : String? = null,
    var strArtistAlternate : String? = null,
    var strLabel           : String? = null,
    var idLabel            : String? = null,
    var intFormedYear      : String? = null,
    var intBornYear        : String? = null,
    var intDiedYear        : String? = null,
    var strDisbanded       : String? = null,
    var strStyle           : String? = null,
    var strGenre           : String? = null,
    var strMood            : String? = null,
    var strWebsite         : String? = null,
    var strFacebook        : String? = null,
    var strTwitter         : String? = null,
    var strBiographyEN     : String? = null,
    var strBiographyDE     : String? = null,
    var strBiographyFR     : String? = null,
    var strBiographyCN     : String? = null,
    var strBiographyIT     : String? = null,
    var strBiographyJP     : String? = null,
    var strBiographyRU     : String? = null,
    var strBiographyES     : String? = null,
    var strBiographyPT     : String? = null,
    var strBiographySE     : String? = null,
    var strBiographyNL     : String? = null,
    var strBiographyHU     : String? = null,
    var strBiographyNO     : String? = null,
    var strBiographyIL     : String? = null,
    var strBiographyPL     : String? = null,
    var strGender          : String? = null,
    var intMembers         : String? = null,
    var strCountry         : String? = null,
    var strCountryCode     : String? = null,
    var strArtistThumb     : String? = null,
    var strArtistLogo      : String? = null,
    var strArtistCutout    : String? = null,
    var strArtistClearart  : String? = null,
    var strArtistWideThumb : String? = null,
    var strArtistFanart    : String? = null,
    var strArtistFanart2   : String? = null,
    var strArtistFanart3   : String? = null,
    var strArtistFanart4   : String? = null,
    var strArtistBanner    : String? = null,
    var strMusicBrainzID   : String? = null,
    var strISNIcode        : String? = null,
    var strLastFMChart     : String? = null,
    var intCharted         : String? = null,
    var strLocked          : String? = null
)

class ArtistsDTO (
    val artists       : List<ArtistDTO>? = null
)