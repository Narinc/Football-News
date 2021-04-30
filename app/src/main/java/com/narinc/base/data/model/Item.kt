package com.narinc.base.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "items")
class Item (
    @PrimaryKey
    val id: String,
): Parcelable