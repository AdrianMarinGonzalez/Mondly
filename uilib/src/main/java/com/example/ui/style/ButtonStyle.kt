package com.example.ui.style

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import com.example.ui.colors.defaultButtonText
import com.example.ui.colors.negativeButtonBorder
import com.example.ui.colors.negativeButtonText
import com.example.ui.colors.primaryButton


sealed class ButtonStyle(
    val backgroundColor: Color = primaryButton,
    val borderColor: Color = Color.Transparent,
    val shape: Shape = RoundedCornerShape(50),
    val textAlign: TextAlign = TextAlign.Center,
    val textColor: Color = defaultButtonText
) {

    data object Negative : ButtonStyle(
        backgroundColor = Color.White,
        borderColor = negativeButtonBorder,
        textColor = negativeButtonText
    )

    data object Positive : ButtonStyle()
}