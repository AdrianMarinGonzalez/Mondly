package com.example.mondly

import com.example.domain.base.Error
import com.example.domain.base.Failure
import com.example.domain.base.Success
import com.example.domain.model.ItemDTO
import com.example.domain.repository.ItemRepository
import com.example.domain.usecase.GetItemUseCase
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class GetItemUseCaseTest {

    @Mock
    lateinit var itemRepository: ItemRepository

    lateinit var getItemUseCase: GetItemUseCase


    @Before
    fun setupCommon() {
        MockitoAnnotations.openMocks(this)
        getItemUseCase = GetItemUseCase(
            itemRepository
        )
    }

    @Test
    fun `when GetItemUseCase executes, data should be retrieved by the ItemRepository`() {
        val id = "1"

        getItemUseCase.execute(id)

        Mockito.verify(itemRepository, Mockito.times(1)).get(id)
    }

    @Test
    fun `when GetItemUseCase retrieves the data, it should return a Success operation`() {
        val id = "1"
        val mockItem = ItemDTO(id)
        Mockito.`when`(itemRepository.get(id)).thenReturn(Success(mockItem))

        val result = getItemUseCase.execute(id)

        Assert.assertEquals(Success(mockItem), result)
    }

    @Test
    fun `when GetItemsUseCase doesn't find data, it should return a NotFound error`() {
        val id = "-1"
        Mockito.`when`(itemRepository.get(id)).thenReturn(Failure(Error.NotFound()))

        val result = getItemUseCase.execute(id)

        Assert.assertEquals(Failure(Error.NotFound()), result)
    }
}