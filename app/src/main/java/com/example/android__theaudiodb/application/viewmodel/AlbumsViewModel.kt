package com.example.android__theaudiodb.application.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android__theaudiodb.domain.album.Album
import com.example.android__theaudiodb.domain.album.AlbumAdapter
import com.example.android__theaudiodb.infrastructure.APIRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class AlbumsViewModel(): ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val albums = MutableLiveData<List<Album>>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getTopFiftyTracks() {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = APIRepository().getTopTenAlbumsOfAllTime()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    albums.postValue(response.body()?.lovedAlbums?.asFlow()?.map{ AlbumAdapter.adapt(it)}?.toList())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    fun getAlbumsByArtistName(artistName: String) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = APIRepository().getAllAlbumsByArtistName(artistName)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body()?.albums != null) {
                        albums.postValue(
                            response.body()?.albums?.asFlow()?.map { AlbumAdapter.adapt(it) }
                                ?.toList()
                        )
                    loading.value = false
                } else {
                    onError("Nothing found !")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}
