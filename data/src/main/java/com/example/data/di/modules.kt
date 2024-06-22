package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.base.HttpClient
import com.example.data.base.MondlyDB
import com.example.data.base.RetrofitInstance
import com.example.data.item.ItemMapper
import com.example.data.item.ItemRepositoryImpl
import com.example.data.item.db.ItemDAO
import com.example.data.item.network.ItemNetworkDatasource
import com.example.data.item.network.ItemService
import org.koin.dsl.module
import retrofit2.Retrofit

fun getModules(applicationContext: Context) = listOf(
    module {
        single { provideHttpClient() }
        single { provideRetrofit(get()) }
        single { provideItemService(get()) }
        factory { ItemNetworkDatasource(get()) }
    },
    module {
        single {
            Room.databaseBuilder(
                applicationContext,
                MondlyDB::class.java,
                "MondlyDB"
            ).build()
        }
        single<ItemDAO> {
            val database = get<MondlyDB>()
            database.itemDAO()
        }
    },
    module {
        factory { ItemRepositoryImpl(get(), get(), get()) }
    }
)

private fun provideHttpClient() =
    HttpClient().buildOKHttpClient()

private fun provideRetrofit(httpClient: HttpClient) = RetrofitInstance(httpClient).get()

private fun provideItemService(retrofit: Retrofit): ItemService =
    retrofit.create(ItemService::class.java)

private fun provideItemMapper() = ItemMapper()