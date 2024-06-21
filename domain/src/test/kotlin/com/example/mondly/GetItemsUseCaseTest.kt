package com.example.mondly

import com.example.domain.base.Error
import com.example.domain.base.Failure
import com.example.domain.base.Success
import com.example.domain.base.get
import com.example.domain.model.ItemDTO
import com.example.domain.repository.ItemRepository
import com.example.domain.usecase.GetItemsUseCase
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class GetItemsUseCaseTest {
    @Mock
    lateinit var itemRepository: ItemRepository

    lateinit var getItemsUseCase: GetItemsUseCase


    @Before
    fun setupCommon() {
        MockitoAnnotations.openMocks(this)
        getItemsUseCase = GetItemsUseCase(
            itemRepository
        )
    }

    @Test
    fun `when GetItemsUseCase executes, data should be retrieved by the ItemRepository`() {
        val mockItem = listOf(ItemDTO("1"), ItemDTO("1"))
        Mockito.`when`(itemRepository.get()).thenReturn(Success(mockItem))

        getItemsUseCase.execute()

        Mockito.verify(itemRepository, Mockito.times(1)).get()
    }

    @Test
    fun `when GetItemsUseCase retrieves the data, it should return a Success operation`() {
        val mockItem = listOf(ItemDTO("1"), ItemDTO("1"))
        Mockito.`when`(itemRepository.get()).thenReturn(Success(mockItem))

        val result = getItemsUseCase.execute()

        Assert.assertEquals(Success(mockItem), result)
    }

    @Test
    fun `when GetItemsUseCase doesn't find data, it should return a NotFound error`() {
        Mockito.`when`(itemRepository.get()).thenReturn(Success(emptyList()))

        val result = getItemsUseCase.execute()

        Assert.assertTrue(result is Failure)
        Assert.assertTrue(result.get() is Error.NotFound)
    }
}