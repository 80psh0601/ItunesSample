package com.example.watcha.ui.track

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.watcha.remote.RemoteData
import com.example.watcha.remote.res.TrackListRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class TrackViewModel @Inject constructor(
    private val remote: RemoteData,
    ) : ViewModel() {

    fun searchTrackList(
        term: String,
        entity: String
    ) = liveData<TrackListRes>(viewModelScope.coroutineContext + Dispatchers.IO) {
        emitSource(MutableLiveData(remote.searchSong(term, entity)))
    }
}