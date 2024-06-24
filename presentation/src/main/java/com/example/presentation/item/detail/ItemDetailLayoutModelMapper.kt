package com.example.presentation.item.detail

import com.example.domain.model.ItemDTO
import com.example.presentation.item.models.ItemDetailUIModel

class ItemDetailLayoutModelMapper {

    fun map(item: ItemDTO) = ItemDetailUIModel.Content(
        id = item.id,
        title = item.name,
        description = item.description,
        iconURL = item.imageUrl
    )
}
