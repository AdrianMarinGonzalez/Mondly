package com.example.presentation.di

import com.example.domain.usecase.GetItemUseCase
import com.example.domain.usecase.GetItemsUseCase
import com.example.presentation.item.detail.ItemDetailLayoutModelMapper
import com.example.presentation.item.detail.ItemDetailViewModel
import com.example.presentation.item.list.ItemListLayoutModelMapper
import com.example.presentation.item.list.ItemListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val modules = listOf(
    module {
        factory { ItemListLayoutModelMapper() }
        factory { ItemDetailLayoutModelMapper() }

        factory { GetItemsUseCase(get()) }
        factory { GetItemUseCase(get()) }

        viewModel { ItemListViewModel(get(), get()) }
        viewModel { ItemDetailViewModel(get(), get()) }
    }
)