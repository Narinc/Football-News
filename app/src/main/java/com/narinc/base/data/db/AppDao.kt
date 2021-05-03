package com.narinc.base.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.narinc.base.data.model.Item

@Dao
interface AppDao {

    @Query("SELECT * FROM articles")
    fun getAllItems():PagingSource<Int, Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(news: List<Item>)

    @Query("DELETE FROM articles")
    suspend fun clearNews()
}