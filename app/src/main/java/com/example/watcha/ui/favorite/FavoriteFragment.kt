package com.example.watcha.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.watcha.R
import com.example.watcha.databinding.FragmentFavoriteBinding
import com.example.watcha.databinding.FragmentTrackBinding
import com.example.watcha.ui.adapter.TrackListAdapter
import com.example.watcha.ui.track.TrackViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val viewModel by activityViewModels<FavoriteViewModel>()
    @Inject lateinit var adapter: TrackListAdapter
    private lateinit var binding: FragmentFavoriteBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoriteBinding.bind(view)
        binding.rvCard.adapter = adapter

        initObserver()
    }

    private fun initObserver() {
        viewModel.getFavoriteList().observe(viewLifecycleOwner, { result ->
            adapter.setItemList(result)
            adapter.setFavoriteList(result)
        })
    }
}