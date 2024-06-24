package com.example.presentation.item.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.base.map
import com.example.domain.usecase.GetItemUseCase
import com.example.presentation.item.models.ItemDetailUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class ItemDetailViewModel(
    private val getItemUseCase: GetItemUseCase,
    private val itemDetailUIModelMapper: ItemDetailLayoutModelMapper
) : ViewModel() {

    private val _item = MutableStateFlow<ItemDetailUIModel>(ItemDetailUIModel.Loading)
    val item: StateFlow<ItemDetailUIModel> = _item.asStateFlow()

    fun getItem(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getItemUseCase.execute(id).map {
                _item.value = itemDetailUIModelMapper.map(it)
            }
        }
    }
}