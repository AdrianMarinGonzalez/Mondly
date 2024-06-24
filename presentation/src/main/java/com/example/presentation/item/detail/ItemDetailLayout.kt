package com.example.presentation.item.detail

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.presentation.R
import com.example.presentation.item.models.ItemDetailUIModel
import com.example.presentation.item.models.ItemListUIModel
import com.example.ui.style.TextStyle
import com.example.ui.widgets.MondlyText
import com.example.ui.widgets.MondlyToolbar
import com.example.ui.widgets.list.MondlyListItem
import com.example.ui.widgets.list.MondlyListItemModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun ItemDetailLayout(
    id: String,
    navigateUp: () -> Unit,
    viewModel: ItemDetailViewModel = koinViewModel()
) {
    val item by viewModel.item.collectAsStateWithLifecycle()

    if (item is ItemDetailUIModel.Loading) {
        viewModel.getItem(id)
        Log.i("ITEMDETAIL", "getItem")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MondlyToolbar(
            title = stringResource(R.string.app_name),
            onBackPressed = navigateUp,
        )

        if (item is ItemDetailUIModel.Loading) {
            EmptyLayout()
            Log.i("ITEMDETAIL", "EMPTY")
        } else {
            Log.i("ITEMDETAIL", "NOT_EMPTY")
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data((item as ItemDetailUIModel.Content).iconURL)
                        .diskCachePolicy(CachePolicy.DISABLED)
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .error(com.example.uilib.R.drawable.list_item_placeholder)
                        .fallback(com.example.uilib.R.drawable.list_item_placeholder)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth(),
                )

                MondlyText(
                    text = (item as ItemDetailUIModel.Content).title,
                    style = TextStyle.Title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 8.dp,
                            end = 8.dp,
                            top = 8.dp,
                            bottom = 8.dp)
                )

                MondlyText(
                    text = (item as ItemDetailUIModel.Content).description,
                    style = TextStyle.Body,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 8.dp,
                            end = 8.dp,
                            top = 8.dp,
                            bottom = 8.dp)
                )

            }
        }
    }
}

@Composable
fun EmptyLayout() {
}

@Preview(showBackground = true, name = "ItemListLayoutPreview", widthDp = 420)
@Composable
fun ItemListLayoutPreview() {
    ItemDetailLayout("1", navigateUp = {})
}
