package com.narinc.base.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "articles")
class Item (
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val url: String,
    val author: String?,
    val title: String,
    val description: String,
    @Json(name = "urlToImage")
    val imgUrl: String?,
    @Json(name = "publishedAt")
    val date: String?,
    val content: String,
    val source: Source,
    var category: String = "",
    var language: String = ""
): Parcelable