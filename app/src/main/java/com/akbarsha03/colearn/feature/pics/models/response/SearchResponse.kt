package com.akbarsha03.colearn.feature.pics.models.response


import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class SearchResponse(
    @Json(name = "results")
    var results: List<SearchResult>? = null,
    @Json(name = "total")
    var total: Int? = null,
    @Json(name = "total_pages")
    var totalPages: Int? = null,
) {
    @Keep
    @JsonClass(generateAdapter = true)
    data class SearchResult(
        @Json(name = "blur_hash")
        var blurHash: String? = null,
        @Json(name = "color")
        var color: String? = null,
        @Json(name = "created_at")
        var createdAt: String? = null,
        @Json(name = "current_user_collections")
        var currentUserCollections: List<Any?>? = null,
        @Json(name = "description")
        var description: String? = null,
        @Json(name = "height")
        var height: Int? = null,
        @Json(name = "id")
        var id: String? = null,
        @Json(name = "liked_by_user")
        var likedByUser: Boolean? = null,
        @Json(name = "likes")
        var likes: Int? = null,
        @Json(name = "links")
        var links: Links? = null,
        @Json(name = "urls")
        var urls: Urls? = null,
        @Json(name = "user")
        var user: User? = null,
        @Json(name = "width")
        var width: Int? = null,
    ) {
        @Keep
        @JsonClass(generateAdapter = true)
        data class Links(
            @Json(name = "download")
            var download: String? = null,
            @Json(name = "html")
            var html: String? = null,
            @Json(name = "self")
            var self: String? = null,
        )

        @Keep
        @JsonClass(generateAdapter = true)
        data class Urls(
            @Json(name = "full")
            var full: String? = null,
            @Json(name = "raw")
            var raw: String? = null,
            @Json(name = "regular")
            var regular: String? = null,
            @Json(name = "small")
            var small: String? = null,
            @Json(name = "thumb")
            var thumb: String? = null,
        )

        @Keep
        @JsonClass(generateAdapter = true)
        data class User(
            @Json(name = "first_name")
            var firstName: String? = null,
            @Json(name = "id")
            var id: String? = null,
            @Json(name = "instagram_username")
            var instagramUsername: String? = null,
            @Json(name = "last_name")
            var lastName: String? = null,
            @Json(name = "links")
            var links: Links? = null,
            @Json(name = "name")
            var name: String? = null,
            @Json(name = "portfolio_url")
            var portfolioUrl: String? = null,
            @Json(name = "profile_image")
            var profileImage: ProfileImage? = null,
            @Json(name = "twitter_username")
            var twitterUsername: String? = null,
            @Json(name = "username")
            var username: String? = null,
        ) {
            @Keep
            @JsonClass(generateAdapter = true)
            data class Links(
                @Json(name = "html")
                var html: String? = null,
                @Json(name = "likes")
                var likes: String? = null,
                @Json(name = "photos")
                var photos: String? = null,
                @Json(name = "self")
                var self: String? = null,
            )

            @Keep
            @JsonClass(generateAdapter = true)
            data class ProfileImage(
                @Json(name = "large")
                var large: String? = null,
                @Json(name = "medium")
                var medium: String? = null,
                @Json(name = "small")
                var small: String? = null,
            )
        }
    }
}