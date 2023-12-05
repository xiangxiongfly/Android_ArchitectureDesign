package com.example.mvvm_coroutines_retrofit_flow_hilt.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CacheEntity(
    val name: String,
    val value: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
