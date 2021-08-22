package com.example.watcha.ui.track

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.watcha.R
import com.example.watcha.databinding.FragmentTrackBinding
import com.example.watcha.ui.adapter.TrackListAdapter
import com.example.watcha.ui.favorite.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TrackFragment : Fragment(R.layout.fragment_track) {

    private val viewModel by activityViewModels<TrackViewModel>()
    private val favoriteViewModel by activityViewModels<FavoriteViewModel>()

    @Inject lateinit var adapter: TrackListAdapter
    private lateinit var binding: FragmentTrackBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTrackBinding.bind(view)
        binding.rvCard.adapter = adapter

        initObserver()
    }

    private fun initObserver() {
        viewModel.searchTrackList(
            term = "greenday",
            entity ="song"
        ).observe(viewLifecycleOwner, { result ->
            adapter.setItemList(result.results)
        })

        favoriteViewModel.getFavoriteList().observe(viewLifecycleOwner, { result ->
            adapter.setFavoriteList(result)
        })
    }
}