package com.example.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ui.base.Action
import com.example.ui.colors.main
import com.example.ui.colors.toolbarTitle
import com.example.ui.style.TextStyle
import com.example.uilib.R


@Composable
fun MondlyToolbar(
    title: String,
    isBackIconVisible: Boolean = true,
    onBackPressed: Action = {},
) {

    TopAppBar(
        backgroundColor = main,
        contentColor = toolbarTitle,
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            ConstraintLayout(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)) {
                val (icon, titleLabel) = createRefs()

                if (isBackIconVisible) {
                    Icon(
                        modifier = Modifier
                            .size(28.dp)
                            .padding(start = 8.dp)
                            .constrainAs(icon) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() },
                            ) {
                                onBackPressed()
                            },
                        painter = painterResource(id = R.drawable.ic_toolbar_back),
                        tint = toolbarTitle,
                        contentDescription = "arrow_back",
                    )
                }
                MondlyText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(titleLabel) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        },
                    style = TextStyle.HeadLine,
                    text = title,
                    color = toolbarTitle,
                    textAlign = TextAlign.Center,
                )
            }

        }
    }
}

@Preview(showBackground = true, name = "MondlyToolbar", widthDp = 420)
@Composable
fun MondlyToolbarPreview() {
    MondlyToolbar(title = "Mondly", onBackPressed = {})
}