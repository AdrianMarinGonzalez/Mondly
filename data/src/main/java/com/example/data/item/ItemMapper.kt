package com.example.data.item

import com.example.data.item.db.ItemEntity
import com.example.data.item.network.GetItemsResponse
import com.example.domain.model.ItemDTO
import com.example.domain.model.NotDefined


class ItemMapper {
    fun map(entities: List<ItemEntity>) = entities.map {
        ItemDTO(
            id = it.id.toString(),
            name = it.name,
            description = it.description
        )
    }

    fun map(response: GetItemsResponse): List<ItemDTO> = response.dataCollection.mapNotNull {
        it.item?.let { item ->
            item.id?.let { id ->
                ItemDTO(
                    id = id,
                    name = item.attributes?.name ?: NotDefined,
                    description = item.attributes?.description ?: NotDefined,
                    imageUrl = item.attributes?.imageInfo?.imageUrl ?: NotDefined
                )
            }
        }
    }

    fun mapToEntity(items: List<ItemDTO>): List<ItemEntity> =
        items.map { ItemEntity(id = it.id.toLong(), name = it.name, description = it.description) }

}