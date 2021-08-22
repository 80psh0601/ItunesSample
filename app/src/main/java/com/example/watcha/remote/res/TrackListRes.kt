package com.example.watcha.remote.res

import com.example.watcha.model.Track

data class TrackListRes (
    var resultCount: String?,
    var results: List<Track>
)