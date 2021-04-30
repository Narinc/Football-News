package com.narinc.base.di.module

import android.app.Application
import androidx.room.Room
import com.narinc.base.data.db.AppDao
import com.narinc.base.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, "app_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideArticleDao(database: AppDatabase): AppDao {
        return database.appDao()
    }

}