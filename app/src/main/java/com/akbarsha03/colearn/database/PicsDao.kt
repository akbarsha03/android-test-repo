package com.akbarsha03.colearn.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PicsDao {
    @Query("SELECT * FROM SearchResultEntity")
    fun getAll(): List<SearchResultEntity>

    @Query("SELECT * FROM SearchResultEntity WHERE id IN (:picID)")
    fun findFavoriteByID(picID: String): List<SearchResultEntity>

    @Insert
    fun insertAll(vararg users: SearchResultEntity)

    @Delete
    fun delete(user: SearchResultEntity)
}