package com.example.data.item

import com.example.data.item.db.ItemDAO
import com.example.data.item.network.ItemNetworkDatasource
import com.example.domain.base.Error
import com.example.domain.base.Failure
import com.example.domain.base.OperationResult
import com.example.domain.base.Success
import com.example.domain.base.map
import com.example.domain.base.mapFailure
import com.example.domain.model.ItemDTO
import com.example.domain.repository.ItemRepository


class ItemRepositoryImpl(
    private val networkDatasource: ItemNetworkDatasource,
    private val itemDAO: ItemDAO,
    private val itemMapper: ItemMapper
) : ItemRepository {
    override fun get(): OperationResult<List<ItemDTO>, Error> {
        return networkDatasource.getItems()
            .map { response ->
                val result = itemMapper.map(response)
                itemDAO.insertItems(itemMapper.mapToEntity(result))
                return Success(result)
            }
            .mapFailure {
                val fallback = itemDAO.getAllItems()
                if (fallback.isNotEmpty()) {
                    return Success(itemMapper.mapFromEntity(fallback))
                } else return Failure(Error.NotFound())
            }
    }

    override fun get(id: String): OperationResult<ItemDTO, Error> {
        itemDAO.getItem(id.toLong())?.let { entity ->
            return Success(itemMapper.mapFromEntity(entity))
        } ?: return Failure(Error.NotFound())
    }
}