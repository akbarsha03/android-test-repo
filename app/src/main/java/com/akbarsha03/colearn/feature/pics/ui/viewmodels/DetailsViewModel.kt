package com.akbarsha03.colearn.feature.pics.ui.viewmodels

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.akbarsha03.colearn.feature.BaseViewModel
import com.akbarsha03.colearn.feature.pics.models.response.SearchResponse
import com.akbarsha03.colearn.feature.pics.usecases.AddToFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPhotos: AddToFavoritesUseCase,
) : BaseViewModel() {

    fun addToFavorites(queryString: SearchResponse.SearchResult): Unit {
        Log.d("unitag", "pointer 1")
        getPhotos.execute(viewModelScope, queryString)
        Log.d("unitag", "pointer 2")
    }
}