package com.akbarsha03.colearn.feature.pics.ui.adapters.state

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class PicsLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<PicsLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: PicsLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): PicsLoadStateViewHolder {
        return PicsLoadStateViewHolder.create(parent, retry)
    }
}