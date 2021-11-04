package com.example.ejemplocompose.vm

import androidx.lifecycle.*
import com.example.ejemplocompose.ItemMenu
import com.example.ejemplocompose.ItemsMenuList

class MainActivityViewModel : ViewModel() {

    val state = liveData {
        emit(State.Loading)
        emit(State.Success(ItemsMenuList().getAll()))
    }

    val itemList = ItemsMenuList().loadItemsMenu()
    val buyList = mutableListOf<ItemMenu>()

    sealed class State {
        object Loading : State()
        class Success(val itemList: List<ItemMenu>) : State()
    }
}