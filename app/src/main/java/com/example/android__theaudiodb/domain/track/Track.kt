package com.example.android__theaudiodb.domain.track

class Track(
    val idTrack: Int?,
    val idAlbum: Int?,
    val idArtist: Int?,
    val idLyric: Int?,
    val name: String?,
    val album: String?,
    val artist: String?,
    val CD: String?,
    val duration: Int?,
    val genre: String?,
    val mood: String?,
    val style: String?,
    val strTheme: String?,
    val descriptionEN: String?,
    val descriptionFR: String?,
    val loved: Int?,
    val score: Double?,
    val scoreVotes: Int?,
    val totalListeners: Int?,
    val totalPlays: Int?,
    val trackImageURL: String?

) {
    override fun toString(): String {
        return "Title(" +
                "idTrack=$idTrack," +
                " idAlbum=$idAlbum," +
                " idArtist=$idArtist," +
                " idLyric=$idLyric," +
                " name='$name'," +
                " album='$album'," +
                " artist='$artist'," +
                " CD='$CD'," +
                " duration=$duration," +
                " genre='$genre'," +
                " mood='$mood'," +
                " style='$style'," +
                " strTheme='$strTheme'," +
                " descriptionEN='$descriptionEN'," +
                " descriptionFR='$descriptionFR'," +
                " loved=$loved," +
                " score=$score," +
                " scoreVotes=$scoreVotes," +
                " totalListeners=$totalListeners," +
                " totalPlays=$totalPlays" +
                ")"
    }
}
