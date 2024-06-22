package com.example.domain.usecase

import com.example.domain.base.OperationResult
import com.example.domain.base.Error
import com.example.domain.base.Failure
import com.example.domain.base.Success
import com.example.domain.base.map
import com.example.domain.base.whenEmpty
import com.example.domain.model.ItemDTO
import com.example.domain.repository.ItemRepository


class GetItemsUseCase constructor(private val itemRepository: ItemRepository) {
    fun execute(): OperationResult<List<ItemDTO>, Error> {
        return itemRepository.get().map {
            it.whenEmpty {
                return Failure(Error.NotFound())
            }
            return Success(it)
        }
    }
}