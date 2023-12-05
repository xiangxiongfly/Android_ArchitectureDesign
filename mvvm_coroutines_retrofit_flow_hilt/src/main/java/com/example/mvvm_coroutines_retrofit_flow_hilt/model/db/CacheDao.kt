package com.example.mvvm_coroutines_retrofit_flow_hilt.model.db

import androidx.room.*
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.entity.CacheEntity

@Dao
interface CacheDao {

    @Query("select id from CacheEntity where name = :name limit 1")
    fun getCacheId(name: String): Long?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCache(cacheEntity: CacheEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCache(cacheEntity: CacheEntity)

    @Query("select * from CacheEntity where name = :name ")
    fun getCache(name: String): CacheEntity?
}