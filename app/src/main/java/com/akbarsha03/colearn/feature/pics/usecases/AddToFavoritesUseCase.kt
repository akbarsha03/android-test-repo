package com.akbarsha03.colearn.feature.pics.usecases

import android.util.Log
import com.akbarsha03.colearn.database.AppDatabase
import com.akbarsha03.colearn.database.SearchResultEntity
import com.akbarsha03.colearn.feature.pics.models.response.SearchResponse
import com.akbarsha03.colearn.utils.usecase.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddToFavoritesUseCase @Inject constructor(
    private val database: AppDatabase,
) :
    UseCase<SearchResponse.SearchResult, Unit>() {
    override fun execute(scope: CoroutineScope, parameters: SearchResponse.SearchResult) {
        Log.d("unitag", "pointer 3")
        val dbEntity = convertToEntity(parameters)
        Log.d("unitag", "pointer 4")
        scope.launch {
            database.picsDao().insertAll(dbEntity)
        }
    }

    private fun convertToEntity(parameters: SearchResponse.SearchResult): SearchResultEntity {
        Log.d("unitag", "pointer 3.a")
        return SearchResultEntity(
            uid = 0,
            id = parameters.id,
            urls = SearchResultEntity.UrlsEntity(
                uid = 0,
                full = parameters.urls?.full,
                raw = parameters.urls?.raw,
                regular = parameters.urls?.regular,
                small = parameters.urls?.small,
                thumb = parameters.urls?.thumb
            ),
        )
    }
}