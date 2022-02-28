package com.example.android__theaudiodb.domain.track

import com.example.android__theaudiodb.application.dto.TrackDTO

class TrackAdapter {
    companion object {
        fun adapt(source: TrackDTO): Track {
            return Track(
                source.idTrack,
                source.idAlbum,
                source.idArtist,
                source.idLyric,
                source.strTrack,
                source.strAlbum,
                source.strArtist,
                source.intCD,
                source.intDuration,
                source.strGenre,
                source.strMood,
                source.strStyle,
                source.strTheme,
                source.strDescription,
                source.strDescriptionFR,
                source.intLoved,
                source.intScore,
                source.intScoreVotes,
                source.intTotalListeners,
                source.intTotalPlays,
                source.strTrackThumb
            )
        }
    }
}