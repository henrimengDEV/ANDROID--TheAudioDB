package com.example.android__theaudiodb.application

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android__theaudiodb.domain.artist.Artist
import com.example.android__theaudiodb.domain.artist.ArtistAdapter
import com.example.android__theaudiodb.infrastructure.APIRepository
import kotlinx.coroutines.*

class MainViewModel(): ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val artist = MutableLiveData<List<Artist>>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getArtist(artistName: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = APIRepository().retrieveArtist(artistName)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    artist.postValue(listOf(ArtistAdapter.adapt(response.body()?.artists?.get(0)!!)))
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
