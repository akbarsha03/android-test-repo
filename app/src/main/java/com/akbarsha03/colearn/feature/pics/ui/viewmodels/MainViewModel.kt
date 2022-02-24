package com.akbarsha03.colearn.feature.pics.ui.viewmodels

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.akbarsha03.colearn.feature.BaseViewModel
import com.akbarsha03.colearn.feature.pics.models.response.ResponseData
import com.akbarsha03.colearn.feature.pics.models.response.SearchResponse
import com.akbarsha03.colearn.feature.pics.ui.QueryData
import com.akbarsha03.colearn.feature.pics.usecases.GetPhotosUseCase
import com.akbarsha03.colearn.feature.pics.usecases.SearchPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPhotos: GetPhotosUseCase,
    private val searchPhotos: SearchPhotosUseCase,
) : BaseViewModel() {

    fun getPhotos(queryString: String): Flow<PagingData<ResponseData.CoverPhoto>> =
        getPhotos.execute(viewModelScope, queryString)

    fun searchPhotos(queryData: QueryData): Flow<PagingData<SearchResponse.SearchResult>> =
        searchPhotos.execute(viewModelScope, queryData)
}