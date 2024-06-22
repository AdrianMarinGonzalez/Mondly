package com.example.ui.widgets

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.style.TextStyle
import com.example.ui.colors.text as textColor


@Composable
fun MondlyText(
    modifier: Modifier = Modifier,
    text: String = "",
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign = TextAlign.Start,
    style: TextStyle,
    color: Color = textColor,
) {

    Text(
        text = text,
        modifier = modifier,
        fontSize = style.fontSize,
        fontFamily = style.fontFamily,
        textAlign = textAlign,
        fontWeight = style.fontWeight,
        color = color,
        maxLines = maxLines,
        textDecoration = style.textDecoration
    )
}

@Preview(showBackground = true, widthDp = 420)
@Composable
fun MondlyTextreview() {
    MondlyText(
        text = "Label",
        style = TextStyle.Label,
    )
}