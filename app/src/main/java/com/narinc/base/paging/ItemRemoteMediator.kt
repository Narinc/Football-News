package com.narinc.base.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.narinc.base.data.api.ApplicationApi
import com.narinc.base.data.db.AppDatabase
import com.narinc.base.data.db.RemoteKey
import com.narinc.base.data.model.Item
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalPagingApi
@Singleton
class ItemRemoteMediator @Inject constructor(
    private val from: String,
    private val to: String,
    private val api: ApplicationApi,
    private val database: AppDatabase
) : RemoteMediator<Int, Item>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Item>): MediatorResult {
        return try {
            val page: Int = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: STARTING_PAGE
                }

                LoadType.PREPEND -> {
                    val remoteKey = getRemoteKeyForFirstItem(state)
                        ?: throw InvalidObjectException("Something went wrong.")
                    remoteKey.prevKey
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    remoteKey.prevKey
                }

                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state)
                    if (remoteKey?.nextKey == null)
                        throw InvalidObjectException("Something went wrong")
                    remoteKey.nextKey
                }
            }

            val response = api.getAllItems(
                from = from,
                to = to,
                page = page,
                pageSize = state.config.pageSize
            )

            val endOfPaginationReached = response.body()?.totalResults == 0

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.remoteKeyDao().clearRemoteKeys()
                    database.appDao().clearNews()
                }

                val prevKey = if (page == STARTING_PAGE) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = response.body()?.articles?.map { article ->
                    RemoteKey(articleId = article.id, nextKey = nextKey, prevKey = prevKey)
                }

                response.body()?.articles?.let { database.appDao().insertAll(it) }
                keys?.let { database.remoteKeyDao().insertAll(it) }
            }
            MediatorResult.Success(endOfPaginationReached = response.body()?.totalResults == 0)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Item>): RemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { articleId ->
                database.remoteKeyDao().remoteKeyByArticle(articleId)
            }
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Item>): RemoteKey? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { lastArticle ->
            database.remoteKeyDao().remoteKeyByArticle(lastArticle.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Item>): RemoteKey? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { firstArticle ->
            database.remoteKeyDao().remoteKeyByArticle(firstArticle.id)
        }
    }

    companion object {
        private const val STARTING_PAGE = 1
    }
}
