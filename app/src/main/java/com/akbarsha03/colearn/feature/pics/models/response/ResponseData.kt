package com.akbarsha03.colearn.feature.pics.models.response


import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class ResponseData(
    @Json(name = "cover_photo")
    var coverPhoto: CoverPhoto? = null,
    @Json(name = "curated")
    var curated: Boolean? = null,
    @Json(name = "description")
    var description: Any? = null,
    @Json(name = "featured")
    var featured: Boolean? = null,
    @Json(name = "id")
    var id: String? = null,
    @Json(name = "last_collected_at")
    var lastCollectedAt: String? = null,
    @Json(name = "links")
    var links: Links? = null,
    @Json(name = "meta")
    var meta: Meta? = null,
    @Json(name = "preview_photos")
    var previewPhotos: List<PreviewPhoto?>? = null,
    @Json(name = "private")
    var `private`: Boolean? = null,
    @Json(name = "published_at")
    var publishedAt: String? = null,
    @Json(name = "share_key")
    var shareKey: String? = null,
    @Json(name = "tags")
    var tags: List<Tag?>? = null,
    @Json(name = "title")
    var title: String? = null,
    @Json(name = "total_photos")
    var totalPhotos: Int? = null,
    @Json(name = "updated_at")
    var updatedAt: String? = null,
    @Json(name = "user")
    var user: User? = null,
) {
    @Keep
    @JsonClass(generateAdapter = true)
    data class CoverPhoto(
        @Json(name = "alt_description")
        var altDescription: String? = null,
        @Json(name = "blur_hash")
        var blurHash: String? = null,
        @Json(name = "categories")
        var categories: List<Any?>? = null,
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
        @Json(name = "promoted_at")
        var promotedAt: Any? = null,
        @Json(name = "sponsorship")
        var sponsorship: Any? = null,
        @Json(name = "updated_at")
        var updatedAt: String? = null,
        @Json(name = "urls")
        var urls: SearchResponse.SearchResult.Urls? = null,
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
            @Json(name = "download_location")
            var downloadLocation: String? = null,
            @Json(name = "html")
            var html: String? = null,
            @Json(name = "self")
            var self: String? = null,
        )

        @Keep
        @JsonClass(generateAdapter = true)
        data class User(
            @Json(name = "accepted_tos")
            var acceptedTos: Boolean? = null,
            @Json(name = "bio")
            var bio: String? = null,
            @Json(name = "first_name")
            var firstName: String? = null,
            @Json(name = "for_hire")
            var forHire: Boolean? = null,
            @Json(name = "id")
            var id: String? = null,
            @Json(name = "instagram_username")
            var instagramUsername: String? = null,
            @Json(name = "last_name")
            var lastName: String? = null,
            @Json(name = "links")
            var links: Links? = null,
            @Json(name = "location")
            var location: String? = null,
            @Json(name = "name")
            var name: String? = null,
            @Json(name = "portfolio_url")
            var portfolioUrl: String? = null,
            @Json(name = "profile_image")
            var profileImage: ProfileImage? = null,
            @Json(name = "total_collections")
            var totalCollections: Int? = null,
            @Json(name = "total_likes")
            var totalLikes: Int? = null,
            @Json(name = "total_photos")
            var totalPhotos: Int? = null,
            @Json(name = "twitter_username")
            var twitterUsername: String? = null,
            @Json(name = "updated_at")
            var updatedAt: String? = null,
            @Json(name = "username")
            var username: String? = null,
        ) {
            @Keep
            @JsonClass(generateAdapter = true)
            data class Links(
                @Json(name = "followers")
                var followers: String? = null,
                @Json(name = "following")
                var following: String? = null,
                @Json(name = "html")
                var html: String? = null,
                @Json(name = "likes")
                var likes: String? = null,
                @Json(name = "photos")
                var photos: String? = null,
                @Json(name = "portfolio")
                var portfolio: String? = null,
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

    @Keep
    @JsonClass(generateAdapter = true)
    data class Links(
        @Json(name = "html")
        var html: String? = null,
        @Json(name = "photos")
        var photos: String? = null,
        @Json(name = "related")
        var related: String? = null,
        @Json(name = "self")
        var self: String? = null,
    )

    @Keep
    @JsonClass(generateAdapter = true)
    data class Meta(
        @Json(name = "description")
        var description: Any? = null,
        @Json(name = "index")
        var index: Boolean? = null,
        @Json(name = "title")
        var title: Any? = null,
    )

    @Keep
    @JsonClass(generateAdapter = true)
    data class PreviewPhoto(
        @Json(name = "blur_hash")
        var blurHash: String? = null,
        @Json(name = "created_at")
        var createdAt: String? = null,
        @Json(name = "id")
        var id: String? = null,
        @Json(name = "updated_at")
        var updatedAt: String? = null,
        @Json(name = "urls")
        var urls: Urls? = null,
    ) {
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
    }

    @Keep
    @JsonClass(generateAdapter = true)
    data class Tag(
        @Json(name = "source")
        var source: Source? = null,
        @Json(name = "title")
        var title: String? = null,
        @Json(name = "type")
        var type: String? = null,
    ) {
        @Keep
        @JsonClass(generateAdapter = true)
        data class Source(
            @Json(name = "ancestry")
            var ancestry: Ancestry? = null,
            @Json(name = "cover_photo")
            var coverPhoto: CoverPhoto? = null,
            @Json(name = "description")
            var description: String? = null,
            @Json(name = "meta_description")
            var metaDescription: String? = null,
            @Json(name = "meta_title")
            var metaTitle: String? = null,
            @Json(name = "subtitle")
            var subtitle: String? = null,
            @Json(name = "title")
            var title: String? = null,
        ) {
            @Keep
            @JsonClass(generateAdapter = true)
            data class Ancestry(
                @Json(name = "category")
                var category: Category? = null,
                @Json(name = "subcategory")
                var subcategory: Subcategory? = null,
                @Json(name = "type")
                var type: Type? = null,
            ) {
                @Keep
                @JsonClass(generateAdapter = true)
                data class Category(
                    @Json(name = "pretty_slug")
                    var prettySlug: String? = null,
                    @Json(name = "slug")
                    var slug: String? = null,
                )

                @Keep
                @JsonClass(generateAdapter = true)
                data class Subcategory(
                    @Json(name = "pretty_slug")
                    var prettySlug: String? = null,
                    @Json(name = "slug")
                    var slug: String? = null,
                )

                @Keep
                @JsonClass(generateAdapter = true)
                data class Type(
                    @Json(name = "pretty_slug")
                    var prettySlug: String? = null,
                    @Json(name = "slug")
                    var slug: String? = null,
                )
            }

            @Keep
            @JsonClass(generateAdapter = true)
            data class CoverPhoto(
                @Json(name = "alt_description")
                var altDescription: String? = null,
                @Json(name = "blur_hash")
                var blurHash: String? = null,
                @Json(name = "categories")
                var categories: List<Any?>? = null,
                @Json(name = "color")
                var color: String? = null,
                @Json(name = "created_at")
                var createdAt: String? = null,
                @Json(name = "current_user_collections")
                var currentUserCollections: List<Any?>? = null,
                @Json(name = "description")
                var description: Any? = null,
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
                @Json(name = "promoted_at")
                var promotedAt: Any? = null,
                @Json(name = "sponsorship")
                var sponsorship: Any? = null,
                @Json(name = "updated_at")
                var updatedAt: String? = null,
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
                    @Json(name = "download_location")
                    var downloadLocation: String? = null,
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
                    @Json(name = "accepted_tos")
                    var acceptedTos: Boolean? = null,
                    @Json(name = "bio")
                    var bio: String? = null,
                    @Json(name = "first_name")
                    var firstName: String? = null,
                    @Json(name = "for_hire")
                    var forHire: Boolean? = null,
                    @Json(name = "id")
                    var id: String? = null,
                    @Json(name = "instagram_username")
                    var instagramUsername: String? = null,
                    @Json(name = "last_name")
                    var lastName: String? = null,
                    @Json(name = "links")
                    var links: Links? = null,
                    @Json(name = "location")
                    var location: String? = null,
                    @Json(name = "name")
                    var name: String? = null,
                    @Json(name = "portfolio_url")
                    var portfolioUrl: String? = null,
                    @Json(name = "profile_image")
                    var profileImage: ProfileImage? = null,
                    @Json(name = "total_collections")
                    var totalCollections: Int? = null,
                    @Json(name = "total_likes")
                    var totalLikes: Int? = null,
                    @Json(name = "total_photos")
                    var totalPhotos: Int? = null,
                    @Json(name = "twitter_username")
                    var twitterUsername: Any? = null,
                    @Json(name = "updated_at")
                    var updatedAt: String? = null,
                    @Json(name = "username")
                    var username: String? = null,
                ) {
                    @Keep
                    @JsonClass(generateAdapter = true)
                    data class Links(
                        @Json(name = "followers")
                        var followers: String? = null,
                        @Json(name = "following")
                        var following: String? = null,
                        @Json(name = "html")
                        var html: String? = null,
                        @Json(name = "likes")
                        var likes: String? = null,
                        @Json(name = "photos")
                        var photos: String? = null,
                        @Json(name = "portfolio")
                        var portfolio: String? = null,
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
    }

    @Keep
    @JsonClass(generateAdapter = true)
    data class User(
        @Json(name = "accepted_tos")
        var acceptedTos: Boolean? = null,
        @Json(name = "bio")
        var bio: Any? = null,
        @Json(name = "first_name")
        var firstName: String? = null,
        @Json(name = "for_hire")
        var forHire: Boolean? = null,
        @Json(name = "id")
        var id: String? = null,
        @Json(name = "instagram_username")
        var instagramUsername: Any? = null,
        @Json(name = "last_name")
        var lastName: String? = null,
        @Json(name = "links")
        var links: Links? = null,
        @Json(name = "location")
        var location: Any? = null,
        @Json(name = "name")
        var name: String? = null,
        @Json(name = "portfolio_url")
        var portfolioUrl: Any? = null,
        @Json(name = "profile_image")
        var profileImage: ProfileImage? = null,
        @Json(name = "total_collections")
        var totalCollections: Int? = null,
        @Json(name = "total_likes")
        var totalLikes: Int? = null,
        @Json(name = "total_photos")
        var totalPhotos: Int? = null,
        @Json(name = "twitter_username")
        var twitterUsername: Any? = null,
        @Json(name = "updated_at")
        var updatedAt: String? = null,
        @Json(name = "username")
        var username: String? = null,
    ) {
        @Keep
        @JsonClass(generateAdapter = true)
        data class Links(
            @Json(name = "followers")
            var followers: String? = null,
            @Json(name = "following")
            var following: String? = null,
            @Json(name = "html")
            var html: String? = null,
            @Json(name = "likes")
            var likes: String? = null,
            @Json(name = "photos")
            var photos: String? = null,
            @Json(name = "portfolio")
            var portfolio: String? = null,
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