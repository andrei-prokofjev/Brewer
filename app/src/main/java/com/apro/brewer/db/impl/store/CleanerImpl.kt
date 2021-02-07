package com.apro.brewer.db.impl.store

import com.apro.brewer.db.api.data.DatabaseClientApi
import com.apro.brewer.db.api.data.store.Cleaner
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CleanerImpl @Inject constructor(private val db: DatabaseClientApi) : Cleaner {
    override fun clearAll() {
        db.clearAll()
    }
}