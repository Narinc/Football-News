package com.narinc.base.data.source

import com.narinc.base.data.api.ApplicationApi
import com.narinc.base.data.db.AppDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val api: ApplicationApi,
    private val database: AppDatabase
) {
}