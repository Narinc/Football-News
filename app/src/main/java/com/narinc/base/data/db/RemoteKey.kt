package com.narinc.base.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remotes")
class RemoteKey(
    @PrimaryKey
    val articleId: String,
    val nextKey: Int?,
    val prevKey: Int?
)