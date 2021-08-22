package com.example.watcha.remote.db

import android.provider.Settings
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.watcha.model.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Dao
interface TrackDao {

    @Query("SELECT * FROM track")
    fun getAllTrack() : LiveData<List<Track>>

    @Query("SELECT * FROM track WHERE trackId = :id")
    fun getTrack(id: Int): LiveData<Track>

    @Query("SELECT EXISTS(SELECT * FROM track WHERE trackId = :id)")
    fun isContain(id : Long) : Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrack(track: Track)

    @Delete
    fun deleteTrack(track: Track)

    fun toggleFavorite(track: Track) {
        GlobalScope.launch(Dispatchers.IO) {
            track.trackId?.let {
                if (isContain(it)) {
                    deleteTrack(track)
                } else {
                    insertTrack(track)
                }
            }
        }
    }
}