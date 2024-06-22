package com.example.data.item.network

import com.example.data.base.NetworkDatasource


class ItemNetworkDatasource constructor(private val service: ItemService): NetworkDatasource() {

    fun getItems() =
        executeCall(
            service.getItems()
        )

}