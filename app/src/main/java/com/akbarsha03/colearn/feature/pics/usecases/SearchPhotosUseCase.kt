package com.akbarsha03.colearn.feature.pics.usecases

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.akbarsha03.colearn.feature.pics.PhotosRepository
import com.akbarsha03.colearn.feature.pics.models.response.SearchResponse
import com.akbarsha03.colearn.feature.pics.ui.QueryData
import com.akbarsha03.colearn.utils.usecase.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchPhotosUseCase @Inject constructor(
    private val repository: PhotosRepository,
) : UseCase<QueryData, Flow<PagingData<SearchResponse.SearchResult>>>() {

    companion object {
        const val DEFAULT_PER_PAGE = 8
    }

    override fun execute(
        scope: CoroutineScope,
        parameters: QueryData,
    ): Flow<PagingData<SearchResponse.SearchResult>> {
        return repository.getSearchResult(parameters).cachedIn(scope)
    }
}