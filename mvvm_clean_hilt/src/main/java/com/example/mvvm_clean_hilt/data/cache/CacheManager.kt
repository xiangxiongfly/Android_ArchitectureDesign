package com.example.mvvm_coroutines_retrofit_flow_hilt.data.cache

import com.example.mvvm_coroutines_retrofit_flow_hilt.data.db.AppDatabase
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.db.dao.CacheDao
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.entity.CacheEntity
import com.google.gson.reflect.TypeToken
import com.hjq.gson.factory.GsonFactory
import javax.inject.Inject

class CacheManager @Inject constructor() {

    @Inject
    lateinit var db: AppDatabase

    @Inject
    lateinit var cacheDao: CacheDao

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

    inline fun <reified T> getCache(name: String): T? {
        val cacheJson = getCache(name)
        cacheJson?.let {
            val type = object : TypeToken<T>() {}.type
            return GsonFactory.getSingletonGson().fromJson<T>(cacheJson, type)
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
