package com.narinc.base.data.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.narinc.base.data.api.ApplicationApi
import com.narinc.base.data.db.AppDatabase
import com.narinc.base.data.model.Item
import com.narinc.base.paging.ItemRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val api: ApplicationApi,
    private val database: AppDatabase
) {

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }

    @ExperimentalPagingApi
    fun getRemoteAndLocalFlow(from: String, to: String): Flow<PagingData<Item>> {
        val pagingSourceFactory =
            {
                database.appDao().getAllItems()
            }
        return Pager(
            config = PagingConfig(NETWORK_PAGE_SIZE, maxSize = 300, enablePlaceholders = true),
            remoteMediator = ItemRemoteMediator(from, to, api, database),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}