package com.example.data.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.item.db.ItemDAO
import com.example.data.item.db.ItemEntity


@Database(entities = [ItemEntity::class], version = 1, exportSchema = false)
abstract class MondlyDB : RoomDatabase() {
    abstract fun itemDAO(): ItemDAO
}
