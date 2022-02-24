package com.akbarsha03.colearn.feature.pics

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.akbarsha03.colearn.feature.pics.models.response.ResponseData
import com.akbarsha03.colearn.feature.pics.models.response.SearchResponse
import com.akbarsha03.colearn.feature.pics.paging.PicsPagingSource
import com.akbarsha03.colearn.feature.pics.paging.SearchPagingSource
import com.akbarsha03.colearn.feature.pics.services.UnSplashApi
import com.akbarsha03.colearn.feature.pics.ui.QueryData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotosRepository @Inject constructor(private val api: UnSplashApi) {

    suspend fun getCollectionPhotosByID(
        id: String,
        page: Int?,
        perPage: Int?,
        orientation: String?,
    ): List<ResponseData.CoverPhoto> =
        api.getCollectionPhotosByID(id, page, perPage, orientation)

    fun getCollectionResult(query: String): Flow<PagingData<ResponseData.CoverPhoto>> =
        Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { PicsPagingSource(api, query) }
        ).flow

    fun getSearchResult(
        queryData: QueryData,
    ): Flow<PagingData<SearchResponse.SearchResult>> =
        Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { SearchPagingSource(api, queryData) }
        ).flow.onStart { emit(PagingData.empty()) }

    companion object {
        const val NETWORK_PAGE_SIZE = 8
    }
}