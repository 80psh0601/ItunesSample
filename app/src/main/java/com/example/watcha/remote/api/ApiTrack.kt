package com.example.watcha.remote.api

import com.example.watcha.remote.res.TrackListRes
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiTrack {
    @GET("search")
    suspend fun searchSong(
        @Query("term") term: String? = null,
        @Query("entity") entity: String? = null)
    : TrackListRes
}