package com.example.baby_journals.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.baby_journals.ui.BabyListUiState
import com.example.baby_journals.utils.formatAge
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BabyListScreen(
    state: StateFlow<BabyListUiState>,
    onAddClick: () -> Unit,
    onOpen: (String) -> Unit
) {
    val ui by state.collectAsState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick
            ) {
                Text("+")
            }
        }
    ) { pad ->
        Column(
            Modifier.padding(pad).padding(16.dp)
        ) {
            Text("Baby Journals", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(12.dp))
            ui.babies.forEach { baby ->
                ElevatedCard(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable{onOpen(baby.id)}
                ) {
                    Column(
                        Modifier.padding(12.dp)
                    ) {
                        Text(baby.name, style = MaterialTheme.typography.titleMedium)
                        val age = formatAge(LocalDate.parse(baby.birthDate))
                        Text("Age: $age")
                        Text("Size: ${baby.heightCm} cm * Weight: ${baby.weightKg} kg")
                    }
                }
            }
            if (ui.babies.isEmpty()){
                Text("No babies yet. Tap = to add one")
            }
        }
    }
}