package com.example.ejemplocompose


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import com.example.ejemplocompose.composes.*
import com.example.ejemplocompose.ui.theme.*
import com.example.ejemplocompose.vm.MainActivityViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainActivityViewModel by viewModels()
        val itemList = viewModel.itemList
        val buyList = viewModel.buyList

        setContent {
            EjemploComposeTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = {
                            Text("Menu Room Service")
                        },
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(Icons.Filled.ArrowBack, "Volver")
                                }
                            })
                    }, content = {
                        MenuScreen(itemList, buyList)
                    }
                )
            }
        }
    }
}
