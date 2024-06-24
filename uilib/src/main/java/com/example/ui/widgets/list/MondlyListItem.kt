package com.example.ui.widgets.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.ui.colors.listItemBackground
import com.example.ui.style.TextStyle
import com.example.ui.widgets.MondlyText
import com.example.uilib.R


@Composable
fun MondlyListItem(
    onItemClick: (String) -> Unit,
    model: MondlyListItemModel,
) {
    Card(
        modifier = Modifier
            .clickable {onItemClick.invoke(model.id) }
            .height(150.dp),
        backgroundColor = listItemBackground,
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(model.iconUrl)
                    .diskCachePolicy(CachePolicy.DISABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .error(R.drawable.list_item_placeholder)
                    .fallback(R.drawable.list_item_placeholder)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(60.dp, 116.dp),
            )

            MondlyText(
                text = model.title,
                style = TextStyle.Label,
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

@Preview(showBackground = true, name = "List Item TikBox", widthDp = 420)
@Composable
fun MondlyListItemPreview() {
    MondlyListItem(
        {},
        MondlyListItemModel("1", "title", "")
    )
}