package com.example.android__theaudiodb.exposition.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android__theaudiodb.domain.track.Track
import com.example.android__theaudiodb.domain.track.TrackAdapter
import com.example.android__theaudiodb.infrastructure.APIRepository
import com.example.android__theaudiodb.infrastructure.SQLiteTracks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

@HiltViewModel
class TracksViewModel @Inject constructor(private val tracksRepository: SQLiteTracks) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val tracks = MutableLiveData<List<Track>>()
    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getTopFiftyTracks() {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = APIRepository().getTopFiftyTracksOfAllTime()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    tracks.postValue(response.body()?.lovedTracks?.asFlow()?.map { TrackAdapter.adapt(it) }?.toList())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    fun getTracksFromAlbum(id: Long) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = APIRepository().getAllTracksFromAlbumId(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    tracks.postValue(response.body()?.tracks?.asFlow()?.map { TrackAdapter.adapt(it) }?.toList())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
//        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}
