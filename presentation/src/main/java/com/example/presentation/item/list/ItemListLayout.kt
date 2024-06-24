package com.example.presentation.item.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.R
import com.example.presentation.item.models.ItemListUIModel
import com.example.ui.widgets.MondlyToolbar
import com.example.ui.widgets.dialog.MondlyDialog
import com.example.ui.widgets.list.MondlyListItem
import com.example.ui.widgets.list.MondlyListItemModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun ItemListLayout(
    selectedItem: (String) -> Unit,
    itemList: List<ItemListUIModel.ItemListItem> = emptyList()
) {
    MainItemListLayout(selectedItem, itemList)
}

@Composable
fun MainItemListLayout(
    selectedItem: (String) -> Unit,
    itemList: List<ItemListUIModel.ItemListItem>,
    viewModel: ItemListViewModel = koinViewModel(),
) {
    val scrollState = rememberLazyStaggeredGridState()
    val fetchedItems by viewModel.items.collectAsStateWithLifecycle()
    var items by rememberSaveable { mutableStateOf(itemList) }

    if (fetchedItems is ItemListUIModel.Content && items.isEmpty()) {
        items = (fetchedItems as ItemListUIModel.Content).items
    } else {
        viewModel.getItems()
    }

    if (fetchedItems is ItemListUIModel.NotFoundError
        || (fetchedItems is ItemListUIModel.Content
            && (fetchedItems as ItemListUIModel.Content).items.isEmpty())
    ) {
        MondlyDialog(
            title = stringResource(R.string.error_alert_title),
            body = stringResource(R.string.error_alert_body),
            positiveText = stringResource(R.string.error_alert_positive),
            positiveAction = {}
        )
    } else {
        ItemListContentLayout(selectedItem, items, scrollState)
    }
}

@Composable
fun ItemListContentLayout(
    selectedItem: (String) -> Unit,
    itemList: List<ItemListUIModel.ItemListItem>,
    scrollState: LazyStaggeredGridState,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MondlyToolbar(
            title = stringResource(R.string.app_name),
            isBackIconVisible = false,
        )

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalItemSpacing = 16.dp,
            state = scrollState,
        ) {
            items(itemList) { item ->
                MondlyListItem(
                    selectedItem,
                    MondlyListItemModel(item.id, item.title, item.iconURL)
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
    ItemListLayout(selectedItem = {})
}
