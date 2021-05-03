package com.narinc.base.data.model

import android.os.Parcelable
import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.parcel.Parcelize

@Parcelize
class Source(val name: String): Parcelable


class SourceConverter{
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @TypeConverter
    fun fromString(value: String): Source?{
        val adapter: JsonAdapter<Source> = moshi.adapter(Source::class.java)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromSourceToString(type: Source): String{
        val adapter: JsonAdapter<Source> = moshi.adapter(Source::class.java)
        return adapter.toJson(type)
    }
}