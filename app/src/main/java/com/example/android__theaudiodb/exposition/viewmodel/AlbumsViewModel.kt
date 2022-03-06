package com.example.android__theaudiodb.exposition.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android__theaudiodb.domain.album.Album
import com.example.android__theaudiodb.domain.album.AlbumAdapter
import com.example.android__theaudiodb.infrastructure.APIRepository
import com.example.android__theaudiodb.infrastructure.SQLiteAlbums
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val albumsRepository: SQLiteAlbums) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val albums = MutableLiveData<List<Album>>()
    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getTopFiftyTracks() {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = APIRepository().getTopTenAlbumsOfAllTime()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    albums.postValue(response.body()?.lovedAlbums?.asFlow()?.map { AlbumAdapter.adapt(it) }?.toList())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    fun getFavoritesAlbums() {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
        val favoriteAlbums: List<Album> = albumsRepository.getFavorites()
            withContext(Dispatchers.Main) {
                albums.postValue(favoriteAlbums)
                println(albums)
                loading.value = false
            }
        }
    }

    fun getAlbumsByArtistName(artistName: String) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = APIRepository().getAllAlbumsByArtistName(artistName)
            val responseAlbums: List<Album>? = response.body()?.albums?.asFlow()?.map { albumsRepository.add(AlbumAdapter.adapt(it)) }?.toList()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body()?.albums != null) {
                    albums.postValue(responseAlbums!!)
                    loading.value = false
                } else {
                    onError("Nothing found !")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
//        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}
