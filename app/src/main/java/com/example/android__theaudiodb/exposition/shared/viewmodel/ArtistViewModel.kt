package com.example.android__theaudiodb.exposition.shared.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android__theaudiodb.domain.artist.Artist
import com.example.android__theaudiodb.domain.artist.ArtistAdapter
import com.example.android__theaudiodb.infrastructure.APIRepository
import com.example.android__theaudiodb.infrastructure.SQLiteAlbumsRepository
import com.example.android__theaudiodb.infrastructure.SQLiteArtistsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(private val artistsRepository: SQLiteArtistsRepository): ViewModel() {
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
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body()?.artists != null) {
                    artist.postValue(listOf(ArtistAdapter.adapt(response.body()?.artists?.get(0)!!)))
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
