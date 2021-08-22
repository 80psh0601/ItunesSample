package com.example.watcha.remote

import com.example.watcha.remote.api.ApiTrack
import com.example.watcha.remote.res.TrackListRes
import javax.inject.Inject

class RemoteData @Inject constructor(
    private val apiTrack: ApiTrack
) {
    suspend fun searchSong(
        term: String,
        entity: String
    ): TrackListRes = apiTrack.searchSong(term, entity)
}