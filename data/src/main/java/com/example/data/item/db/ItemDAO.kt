package com.example.data.item.db


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: ItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(items: List<ItemEntity>)

    @Query("SELECT * FROM items WHERE id = :id")
    fun getItem(id: Long): ItemEntity?

    @Query("SELECT * FROM items")
    fun getAllItems(): List<ItemEntity>
}