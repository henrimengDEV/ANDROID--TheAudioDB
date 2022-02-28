package com.example.android__theaudiodb.application.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android__theaudiodb.domain.Track
import com.example.android__theaudiodb.domain.TrackAdapter
import com.example.android__theaudiodb.infrastructure.APIRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class TracksViewModel(): ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val tracks = MutableLiveData<List<Track>>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getTopFiftyTracks() {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = APIRepository().getTopFiftyTracksOfAllTime()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    tracks.postValue(response.body()?.loved?.asFlow()?.map{TrackAdapter.adapt(it)}?.toList())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}
