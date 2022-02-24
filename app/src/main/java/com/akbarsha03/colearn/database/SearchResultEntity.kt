package com.akbarsha03.colearn.database

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity
data class SearchResultEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "blur_hash")
    var blurHash: String? = null,
    @ColumnInfo(name = "color")
    var color: String? = null,
    @ColumnInfo(name = "created_at")
    var createdAt: String? = null,
    @ColumnInfo(name = "current_user_collections")
    var currentUserCollections: List<Any?>? = null,
    @ColumnInfo(name = "description")
    var description: String? = null,
    @ColumnInfo(name = "height")
    var height: Int? = null,
    @ColumnInfo(name = "id")
    var id: String? = null,
    @ColumnInfo(name = "liked_by_user")
    var likedByUser: Boolean? = null,
    @ColumnInfo(name = "likes")
    var likes: Int? = null,
    @ColumnInfo(name = "links")
    var links: LinksEntity? = null,
    @ColumnInfo(name = "urls")
    var urls: UrlsEntity? = null,
    @ColumnInfo(name = "user")
    var user: UserEntity? = null,
    @ColumnInfo(name = "width")
    var width: Int? = null,
) {
    @Keep
    @Entity
    data class LinksEntity(
        @PrimaryKey(autoGenerate = true) val uid: Int,
        @ColumnInfo(name = "download")
        var download: String? = null,
        @ColumnInfo(name = "html")
        var html: String? = null,
        @ColumnInfo(name = "self")
        var self: String? = null,
    )

    @Keep
    @Entity
    data class UrlsEntity(
        @PrimaryKey(autoGenerate = true) val uid: Int,
        @ColumnInfo(name = "full")
        var full: String? = null,
        @ColumnInfo(name = "raw")
        var raw: String? = null,
        @ColumnInfo(name = "regular")
        var regular: String? = null,
        @ColumnInfo(name = "small")
        var small: String? = null,
        @ColumnInfo(name = "thumb")
        var thumb: String? = null,
    )

    @Keep
    @Entity
    data class UserEntity(
        @PrimaryKey(autoGenerate = true) val uid: Int,
        @ColumnInfo(name = "first_name")
        var firstName: String? = null,
        @ColumnInfo(name = "id")
        var id: String? = null,
        @ColumnInfo(name = "instagram_username")
        var instagramUsername: String? = null,
        @ColumnInfo(name = "last_name")
        var lastName: String? = null,
        @ColumnInfo(name = "links")
        var links: LinksEntity? = null,
        @ColumnInfo(name = "name")
        var name: String? = null,
        @ColumnInfo(name = "portfolio_url")
        var portfolioUrl: String? = null,
        @ColumnInfo(name = "profile_image")
        var profileImage: ProfileImageEntity? = null,
        @ColumnInfo(name = "twitter_username")
        var twitterUsername: String? = null,
        @ColumnInfo(name = "username")
        var username: String? = null,
    ) {
        @Keep
        @Entity
        data class LinksEntity(
            @PrimaryKey(autoGenerate = true) val uid: Int,
            @ColumnInfo(name = "html")
            var html: String? = null,
            @ColumnInfo(name = "likes")
            var likes: String? = null,
            @ColumnInfo(name = "photos")
            var photos: String? = null,
            @ColumnInfo(name = "self")
            var self: String? = null,
        )

        @Keep
        @Entity
        data class ProfileImageEntity(
            @PrimaryKey(autoGenerate = true) val uid: Int,
            @ColumnInfo(name = "large")
            var large: String? = null,
            @ColumnInfo(name = "medium")
            var medium: String? = null,
            @ColumnInfo(name = "small")
            var small: String? = null,
        )
    }
}