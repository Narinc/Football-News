package com.narinc.base.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.narinc.base.data.model.Item
import com.narinc.base.data.model.SourceConverter

@Database(entities = [Item::class, RemoteKey::class], version = 1, exportSchema = false)
@TypeConverters(value = [SourceConverter::class])
abstract class AppDatabase : RoomDatabase(){
    abstract fun appDao(): AppDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}