package com.example.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.style.ButtonStyle
import com.example.ui.style.TextStyle


@Composable
fun MondlyButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String = "",
    style: ButtonStyle,
    content: @Composable RowScope.() -> Unit = {}
) {

    Button(
        modifier = modifier,
        border = if (style is ButtonStyle.Negative) {
            BorderStroke(1.dp, style.borderColor)
        } else {
            null
        },
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = style.textColor,
            backgroundColor = style.backgroundColor,
        ),
        shape = style.shape,
        onClick = { onClick() },
    ) {
        MondlyText(
            modifier = Modifier.padding(
                top = 8.dp,
                bottom = 8.dp,
            ),
            textAlign = style.textAlign,
            text = text,
            style = TextStyle.Button,
            color = style.textColor,
        )
        content()
    }
}

@Preview(showBackground = true, widthDp = 420)
@Composable
fun PositiveButtonPreview() {
    MondlyButton(
        modifier = Modifier
            .fillMaxWidth(),
        text = "Positive",
        onClick = {},
        style = ButtonStyle.Positive,
    )
}

@Preview(showBackground = true, widthDp = 420)
@Composable
fun NegativeButtonPreview() {
    MondlyButton(
        modifier = Modifier
            .fillMaxWidth(),
        text = "Negative",
        onClick = {},
        style = ButtonStyle.Negative,
    )
}
