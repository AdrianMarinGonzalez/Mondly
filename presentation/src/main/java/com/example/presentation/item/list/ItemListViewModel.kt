package com.example.presentation.item.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.base.map
import com.example.domain.base.mapFailure
import com.example.domain.usecase.GetItemsUseCase
import com.example.presentation.item.models.ItemListUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class ItemListViewModel constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val itemListUIModelMapper: ItemListLayoutModelMapper
) : ViewModel() {

    private val _items = MutableStateFlow<ItemListUIModel>(ItemListUIModel.Loading)
    val items: StateFlow<ItemListUIModel> = _items.asStateFlow()

    fun getItems() {
        viewModelScope.launch(Dispatchers.IO) {
            getItemsUseCase.execute().map {
                _items.value = itemListUIModelMapper.map(it)
            }.mapFailure {
                _items.value = ItemListUIModel.NotFoundError
            }
        }
    }
}