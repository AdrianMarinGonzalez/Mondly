package com.example.ui.widgets.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ui.base.Action
import com.example.ui.colors.dialogBackground
import com.example.ui.style.ButtonStyle
import com.example.ui.style.TextStyle
import com.example.ui.widgets.MondlyButton
import com.example.ui.widgets.MondlyText
import com.example.uilib.R


@Composable
fun MondlyDialog(
    title: String = "",
    body: String = "",
    positiveText: String = "",
    negativeText: String = "",
    positiveAction: Action = { },
    negativeAction: Action = { },
) {
    val shouldDismiss = remember { mutableStateOf(false) }
    if (shouldDismiss.value) return

    Dialog(
        onDismissRequest = {
            shouldDismiss.value = true
        }, properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        ConstraintLayout {
            val (icon, layout) = createRefs()
            Image(
                painter = painterResource(R.drawable.info_dialog_icon),
                contentDescription = "avatar",
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .border(4.dp, dialogBackground, CircleShape)
                    .background(dialogBackground)
                    .constrainAs(icon) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .zIndex(1f),
            )

            Column(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(dialogBackground)
                    .fillMaxWidth()
                    .constrainAs(layout) {
                        top.linkTo(icon.top, 24.dp)
                    }
                    .clip(RoundedCornerShape(16.dp))
            ) {
                if (title.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 32.dp,
                                bottom = 8.dp,
                                start = 16.dp,
                                end = 16.dp
                            ),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        MondlyText(
                            text = title,
                            style = TextStyle.Title
                        )
                    }
                }

                if (body.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = 8.dp,
                                start = 16.dp,
                                end = 16.dp
                            ),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        MondlyText(
                            text = body,
                            style = TextStyle.Body,
                        )
                    }
                }
                if (positiveText.isNotEmpty()) {
                    MondlyButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 12.dp,
                                end = 12.dp,
                                top = 8.dp,
                                bottom = 8.dp
                            ),
                        onClick = {
                            positiveAction()
                            shouldDismiss.value = true
                        },
                        text = positiveText,
                        style = ButtonStyle.Positive
                    )
                }
                if (negativeText.isNotEmpty()) {
                    MondlyButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 12.dp,
                                end = 12.dp,
                                bottom = 8.dp
                            ),
                        text = negativeText,
                        onClick = {
                            negativeAction()
                            shouldDismiss.value = true
                        },
                        style = ButtonStyle.Negative
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 420)
@Composable
private fun DialogPreview() {
    MondlyDialog(
        title = "Title",
        body = "Body",
        positiveText = "Positive Text",
        negativeText = "Negative Text",
        positiveAction = {},
        negativeAction = {},
    )
}