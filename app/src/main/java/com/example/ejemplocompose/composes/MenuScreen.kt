package com.example.ejemplocompose.composes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ejemplocompose.ItemMenu
import com.example.ejemplocompose.vm.MainActivityViewModel

@Composable
fun MenuScreen(itemList: List<ItemMenu>, buyList: MutableList<ItemMenu>) {
    val viewModel: MainActivityViewModel = viewModel()
    val state = viewModel.state.observeAsState()

    when (state.value) {
        is MainActivityViewModel.State.Loading -> LoadingScreen()
        is MainActivityViewModel.State.Success -> {
            buyList.add(itemList[2])
            Surface(Modifier.padding(all = 8.dp)) {
                Column(Modifier.fillMaxHeight()) {
                    Text(
                        "Pedido para habitación ###",
                        textAlign = TextAlign.Center,
                        fontSize = 36.sp
                    )
                    LazyColumn {
                        items(itemList) { item ->
                            ItemView(item, buyList)
                        }
                    }
                    Box(modifier = Modifier.fillMaxSize(), Alignment.BottomStart) {
                        TotalView(buyList)
                    }
                }
            }
        }
    }
}

@Composable
fun ItemView(item: ItemMenu, buyList: MutableList<ItemMenu>) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column {
            Text(item.nombre, fontSize = 32.sp)
            Text("$${item.precio}", fontSize = 32.sp)
        }
        CantidadView(item, buyList)
    }
    Column {
        Divider(
            color = Color.Gray,
            thickness = 1.dp
        )
    }
}

@Composable
fun CantidadView(item: ItemMenu, buyList: MutableList<ItemMenu>) {
    val cantidadEnLista: Int = buyList.count { it == item }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        Boton("-") { if (buyList.contains(item)) buyList.remove(item) }
        Text(
            cantidadEnLista.toString(),
            fontSize = 36.sp,
            modifier = Modifier.padding(horizontal = 7.dp)
        )
        Boton("+") { buyList.add(item) }
    }
}

@Composable
fun Boton(
    string: String,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        Modifier.size(50.dp)
    ) {
        if (string == "-") {
            Icon(
                Icons.Filled.KeyboardArrowLeft,
                contentDescription = "Restar",
                tint = Color.Red,
                modifier = Modifier.size(40.dp)
            )
        }
        if (string == "+") {
            Icon(
                Icons.Filled.KeyboardArrowRight,
                contentDescription = "Sumar",
                tint = Color.Red,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Composable
fun TotalView(buyList: MutableList<ItemMenu>) {
    var total = 0
    for (item in buyList) {
        total += item.precio.toInt()
    }
    Text("Total: $${total}", fontSize = 36.sp)
}

@Composable
fun LoadingScreen() {
    Surface(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        CircularProgressIndicator(
            Modifier
                .wrapContentHeight()
                .wrapContentWidth()
        )
    }
}