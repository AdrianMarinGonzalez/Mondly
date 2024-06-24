package com.example.presentation.item.list

import com.example.domain.model.ItemDTO
import com.example.presentation.item.models.ItemListUIModel

class ItemListLayoutModelMapper {

    fun map(items: List<ItemDTO>) =
        ItemListUIModel.Content(items = items.map {
            ItemListUIModel.ItemListItem(
                id = it.id,
                title = it.name,
                iconURL = it.imageUrl
            )
        })
}
