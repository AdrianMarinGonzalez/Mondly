package com.example.data.item.db


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.NotDefined

@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey(autoGenerate = false) val id: Long = 0,
    val name: String = NotDefined,
    val description: String = NotDefined,
)