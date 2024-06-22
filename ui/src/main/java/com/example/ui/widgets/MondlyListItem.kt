package com.example.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.ui.R
import com.example.ui.style.TextStyle


@Composable
fun MondlyListItem(
    iconURL: String = "",
    title: String = "",
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = CenterVertically,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(iconURL)
                .diskCachePolicy(CachePolicy.DISABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .error(R.drawable.list_item_placeholder)
                .fallback(R.drawable.list_item_placeholder)
                .build(),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
        )

        Column {
            MondlyText(
                text = title,
                style = TextStyle.Label,
            )

        }
    }
}

@Preview(showBackground = true, name = "List Item TikBox", widthDp = 420)
@Composable
fun MondlyListItemPreview() {
    MondlyListItem(
        iconURL = "",
        title = "Title"
    )
}