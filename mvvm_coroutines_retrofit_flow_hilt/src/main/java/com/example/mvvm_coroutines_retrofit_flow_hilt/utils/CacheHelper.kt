package com.example.mvvm_coroutines_retrofit_flow_hilt.utils

import com.example.mvvm_coroutines_retrofit_flow_hilt.model.db.AppDatabase
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.db.CacheDao
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.entity.CacheEntity
import com.hjq.gson.factory.GsonFactory
import java.lang.reflect.Type
import javax.inject.Inject

class CacheHelper @Inject constructor() {
    @Inject
    lateinit var cacheDao: CacheDao

    @Inject
    lateinit var db: AppDatabase

    fun saveCache(name: String, obj: Any) {
        db.runInTransaction {
            val id = cacheDao.getCacheId(name)
            if (id == null) {
                cacheDao.insertCache(
                    CacheEntity(
                        name,
                        GsonFactory.getSingletonGson().toJson(obj)
                    )
                )
            } else {
                cacheDao.updateCache(
                    CacheEntity(
                        name,
                        GsonFactory.getSingletonGson().toJson(obj)
                    )
                )
            }
        }
    }

    fun <T> getCache(name: String, typeOf: Type): T? {
        val json = getCache(name)
        if (json != null) {
            return GsonFactory.getSingletonGson().fromJson(json, typeOf)
        }
        return null
    }

    fun getCache(name: String): String? {
        val cacheEntity = cacheDao.getCache(name)
        if (cacheEntity != null) {
            return cacheEntity.value
        }
        return null
    }
}