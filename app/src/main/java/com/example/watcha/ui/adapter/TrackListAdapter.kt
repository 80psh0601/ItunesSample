package com.example.watcha.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.example.watcha.databinding.ItemTrackBinding
import com.example.watcha.model.Track
import com.example.watcha.remote.db.TrackDao
import com.example.watcha.ui.favorite.FavoriteViewModel
import javax.inject.Inject

@SuppressLint("NotifyDataSetChanged")

class TrackListAdapter@Inject constructor(
    private val remote: TrackDao
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemList = listOf<Track>()
    val favoriteList = ObservableArrayList<Track>()

    fun setFavoriteList(list: List<Track>) {
        favoriteList.clear()
        favoriteList.addAll(list)
    }

    fun setItemList(itemList: List<Track>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding = ItemTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.onBind(itemList[position])
        }
    }

    private inner class MainViewHolder(
        val binding: ItemTrackBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Track) {
            binding.apply {
                data = item
                db = remote
                favorite = favoriteList
            }
        }
    }
}