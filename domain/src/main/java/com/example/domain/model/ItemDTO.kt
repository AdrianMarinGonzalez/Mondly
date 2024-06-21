package com.example.domain.model

const val NotDefined = "N/D"
data class ItemDTO(
    val id: String,
    val name: String = NotDefined,
    val description: String = NotDefined,
    val imageUrl: String = NotDefined
)