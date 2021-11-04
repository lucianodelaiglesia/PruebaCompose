package com.example.ejemplocompose

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ItemsMenuList {
    fun loadItemsMenu(): List<ItemMenu> {
        return listOf(
            ItemMenu("Hamburguesa", "200"),
            ItemMenu("Napolitana", "200"),
            ItemMenu("Tortilla", "300"),
            ItemMenu("Fritas", "100"),
            ItemMenu("Agua", "50"),
            ItemMenu("Gaseosa", "80")
        )
    }

    suspend fun getAll() = withContext(Dispatchers.IO) {
        delay(2000)
        loadItemsMenu()
    }
}
