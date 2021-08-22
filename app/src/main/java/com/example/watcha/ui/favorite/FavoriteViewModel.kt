package com.example.watcha.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.watcha.model.Track
import com.example.watcha.remote.db.TrackDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val remote: TrackDao,
) : ViewModel() {

    fun getFavoriteList() = liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
        emitSource(remote.getAllTrack())
    }
}