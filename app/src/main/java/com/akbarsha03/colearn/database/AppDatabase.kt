package com.akbarsha03.colearn.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SearchResultEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun picsDao(): PicsDao
}