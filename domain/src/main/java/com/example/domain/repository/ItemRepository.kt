package com.example.domain.repository

import com.example.domain.base.Error
import com.example.domain.base.OperationResult
import com.example.domain.model.ItemDTO
import kotlinx.coroutines.flow.Flow


interface ItemRepository {

    fun get(): OperationResult<List<ItemDTO>, Error>
    fun get(id: String): OperationResult<ItemDTO, Error>

}