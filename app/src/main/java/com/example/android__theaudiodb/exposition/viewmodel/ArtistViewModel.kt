package com.example.android__theaudiodb.exposition.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android__theaudiodb.domain.album.Album
import com.example.android__theaudiodb.domain.artist.Artist
import com.example.android__theaudiodb.domain.artist.ArtistAdapter
import com.example.android__theaudiodb.infrastructure.APIRepository
import com.example.android__theaudiodb.infrastructure.SQLiteArtists
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(private val artistsRepository: SQLiteArtists): ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val artist = MutableLiveData<List<Artist>>()

    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getArtist(artistName: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = APIRepository().getArtist(artistName)
            val responseArtists = listOf(artistsRepository.add(ArtistAdapter.adapt(response.body()?.artists?.get(0)!!)))

            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body()?.artists != null) {
                    artist.postValue(responseArtists)
                    loading.value = false
                } else {
                    onError("Nothing found !")
                }
            }
        }
    }

    fun getFavoritesArtist() {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val favoriteArtist: List<Artist> = artistsRepository.getFavorites()
            withContext(Dispatchers.Main) {
                artist.postValue(favoriteArtist)
                println(artist)
                loading.value = false
            }
        }
    }

    fun updateArtist(artist: Artist) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            artistsRepository.add(artist)
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
