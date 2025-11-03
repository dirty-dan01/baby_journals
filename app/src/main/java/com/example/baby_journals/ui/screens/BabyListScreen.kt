package com.example.baby_journals.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.example.baby_journals.ui.BabyListUiState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun BabyListScreen(
    state: StateFlow<BabyListUiState>,
    onAddClick: () -> Unit,
    onOpen: (String) -> Unit
){

}