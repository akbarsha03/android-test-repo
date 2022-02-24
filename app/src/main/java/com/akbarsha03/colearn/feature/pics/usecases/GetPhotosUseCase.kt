package com.akbarsha03.colearn.feature.pics.usecases

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.akbarsha03.colearn.feature.pics.PhotosRepository
import com.akbarsha03.colearn.feature.pics.models.response.ResponseData
import com.akbarsha03.colearn.utils.usecase.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPhotosUseCase @Inject constructor(
    private val repository: PhotosRepository,
) : UseCase<String, Flow<PagingData<ResponseData.CoverPhoto>>>() {

    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<ResponseData.CoverPhoto>>? = null

    companion object {
        const val DEFAULT_PER_PAGE = 8
    }

    override fun execute(
        scope: CoroutineScope,
        parameters: String,
    ): Flow<PagingData<ResponseData.CoverPhoto>> {

        val lastResult = currentSearchResult
        if (parameters == currentQueryValue && lastResult != null) {
            return lastResult
        }

        currentQueryValue = parameters

        val newResult: Flow<PagingData<ResponseData.CoverPhoto>> =
            repository.getCollectionResult(currentQueryValue ?: "")
                .cachedIn(scope)
        currentSearchResult = newResult

        return newResult
    }
}