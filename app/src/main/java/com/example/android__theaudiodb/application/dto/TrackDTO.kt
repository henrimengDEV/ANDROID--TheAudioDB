package com.example.android__theaudiodb.application.dto

import com.google.gson.annotations.SerializedName

data class TrackDTO (
    var idTrack : Int,
    var idAlbum : Int,
    var idArtist : Int,
    var idLyric : Int,
    var strTrack : String,
    var strAlbum : String,
    var strArtist : String,
    var strArtistAlternate : String,
    var intCD : String,
    var intDuration : Int,
    var strGenre : String,
    var strMood : String,
    var strStyle : String,
    var strTheme : String,
    var strDescription : String,
    var strDescriptionDE : String,
    var strDescriptionFR : String,
    var strDescriptionCN : String,
    var strDescriptionIT : String,
    var strDescriptionJP : String,
    var strDescriptionRU : String,
    var strDescriptionES : String,
    var strDescriptionPT : String,
    var strDescriptionSE : String,
    var strDescriptionNL : String,
    var strDescriptionHU : String,
    var strDescriptionNO : String,
    var strDescriptionIL : String,
    var strDescriptionPL : String,
    var strTrackThumb : String,
    var strTrack3DCase : String,
    var strTrackLyrics : String,
    var strMusicVid : String,
    var strMusicVidDirector : String,
    var strMusicVidCompany : String,
    var strMusicVidScreen1 : String,
    var strMusicVidScreen2 : String,
    var strMusicVidScreen3 : String,
    var intTrackNumber : Int,
    var intLoved : Int,
    var intScore : Double,
    var intScoreVotes : Int,
    var intTotalListeners : Int,
    var intTotalPlays : Int,
    var strMusicBrainzID : String,
    var strMusicBrainzAlbumID : String,
    var strMusicBrainzArtistID : String,
    var strLocked : String
)

class LovedTracksDTO (
    @SerializedName("loved" ) var lovedTracks       : List<TrackDTO>? = null
)