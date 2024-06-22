package com.example.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.example.ui.base.Action
import com.example.ui.colors.main
import com.example.ui.colors.toolbarTitle
import com.example.ui.style.TextStyle


@Composable
fun MondlyToolbar(
    title: String,
    isBackIconVisible: Boolean = true,
    onBackPressed: Action,
) {
    Row(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 16.dp)
            .background(main),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (isBackIconVisible) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                    ) {
                        onBackPressed()
                    },
                painter = painterResource(id = R.drawable.ic_toolbar_back),
                contentDescription = "arrow_back",
            )
        }
        MondlyText(
            modifier = Modifier
                .fillMaxWidth(),
            style = TextStyle.HeadLine,
            text = title,
            color = toolbarTitle,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true, name = "MondlyToolbar", widthDp = 420)
@Composable
fun MondlyToolbarPreview() {
    MondlyToolbar(title = "Mondly", onBackPressed = {})
}