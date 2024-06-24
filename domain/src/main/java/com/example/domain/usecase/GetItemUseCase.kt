package com.example.domain.usecase

import com.example.domain.base.OperationResult
import com.example.domain.base.Error
import com.example.domain.model.ItemDTO
import com.example.domain.repository.ItemRepository


class GetItemUseCase(private val itemRepository: ItemRepository) {
    fun execute(id: String): OperationResult<ItemDTO, Error> = itemRepository.get(id)
}