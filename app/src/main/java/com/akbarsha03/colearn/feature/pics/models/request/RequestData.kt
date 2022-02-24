package com.akbarsha03.colearn.feature.pics.models.request

data class RequestData(
    var page: Int? = 0,
    var perPage: Int? = 1,
    var id: String? = null,
    var orientation: String? = null
)