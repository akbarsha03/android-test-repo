package com.akbarsha03.colearn.feature.pics.ui

data class QueryData(
    var query: String,
    var colorFilter: String? = null,
    var sortBy: String? = null,
    var collections: String? = null,
)