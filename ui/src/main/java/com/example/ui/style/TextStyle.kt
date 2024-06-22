package com.example.ui.style

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.ui.fonts.nunito


sealed class TextStyle(
    val fontSize: TextUnit = 20.sp,
    val fontWeight: FontWeight = FontWeight.Normal,
    val fontFamily: FontFamily = nunito,
    val textDecoration: TextDecoration = TextDecoration.None
) {
    data object HeadLine : TextStyle(
        fontSize = 20.sp,
        fontFamily = nunito,
        fontWeight = FontWeight.Bold
    )

    data object Title : TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
    )

    data object Body : TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = nunito,
    )

    data object Button : TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
    )

    data object Label : TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = nunito,
    )
}