package com.example.baby_journals.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AboveLabel(text: String) {
    Text(
        text = text,
        style = androidx.compose.material3.MaterialTheme.typography.labelSmall
    )
}