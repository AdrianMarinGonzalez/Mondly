package com.example.presentation.item.models


sealed class ItemDetailUIModel {
    data object Loading: ItemDetailUIModel()
    data class Content(
        val id: String,
        val title: String,
        val description: String,
        val iconURL: String): ItemDetailUIModel()
}
