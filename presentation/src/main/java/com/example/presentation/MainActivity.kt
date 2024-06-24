package com.example.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentation.item.ItemNavigation
import com.example.presentation.item.list.ItemListLayout

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ItemNavigation()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    ItemListLayout(selectedItem = {})
}