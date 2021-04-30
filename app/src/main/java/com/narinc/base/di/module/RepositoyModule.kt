package com.narinc.base.di.module

import com.narinc.base.data.api.ApplicationApi
import com.narinc.base.data.db.AppDatabase
import com.narinc.base.data.source.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAppRepository(
        api: ApplicationApi,
        database: AppDatabase
    ): AppRepository {
        return AppRepository(api, database)
    }
}