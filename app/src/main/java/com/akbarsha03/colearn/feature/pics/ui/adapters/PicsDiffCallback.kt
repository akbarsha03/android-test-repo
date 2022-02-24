package com.akbarsha03.colearn.feature.pics.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.akbarsha03.colearn.feature.pics.models.response.ResponseData

class PicsDiffCallback(
    private val oldItems: List<ResponseData.CoverPhoto>,
    private val newItems: List<ResponseData.CoverPhoto>
) :
    DiffUtil.Callback() {
    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldItems[oldItemPosition].id == newItems[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.blurHash.equals(newItem.blurHash);
    }
}