package com.example.watcha.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "track")
data class Track (
    @PrimaryKey
    var trackId: Long?,
    var trackName: String?,
    var collectionName: String?,
    var artistName: String?,
    var artworkUrl60: String?
)