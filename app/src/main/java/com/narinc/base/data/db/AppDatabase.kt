package com.narinc.base.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.narinc.base.data.model.Item

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun appDao(): AppDao
}