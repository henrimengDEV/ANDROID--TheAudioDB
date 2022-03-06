package com.example.android__theaudiodb.exposition.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android__theaudiodb.domain.album.Album
import com.example.android__theaudiodb.infrastructure.SQLiteAlbums
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(private val albums: SQLiteAlbums) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    var job: Job? = null
    val loading = MutableLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun updateAlbum(album: Album) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            albums.add(album)
        }
    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
        loading.value = false
    }
}