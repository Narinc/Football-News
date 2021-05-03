package com.narinc.base.data.api

import com.narinc.base.BuildConfig
import com.narinc.base.data.model.ItemList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApplicationApi {
    @GET("everything")
    suspend fun getAllItems(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("q") search: String = "football",
        @Query("from") from: String = "",
        @Query("to") to: String = "",
        @Query("page") page: Int = 0,
        @Query("pageSize") pageSize: Int = 0
    ): Response<ItemList>
}