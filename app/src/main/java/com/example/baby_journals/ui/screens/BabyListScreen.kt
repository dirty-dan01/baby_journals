package com.example.baby_journals.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val isEmpty = ui.babies.isEmpty()
    Scaffold(
        floatingActionButton = {
            if (!isEmpty) {
                FloatingActionButton(
                    onClick = onAddClick
                ) {
                    Text("+")
                }
            }
        }
    ) { pad ->
        if (isEmpty) {
            Box(
                modifier = Modifier
                    .padding(pad)
                    .fillMaxSize()
                    .clickable { onAddClick() },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "+",
                        fontSize = 72.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xfffc90b4)
                    )
                    Spacer(Modifier.padding(16.dp))
                    Text(
                        "Add Your Baby",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.padding(16.dp))
                    Text(
                        "Start tracking every important\nmoment from day one",
                        fontSize = 14.sp,
                        lineHeight = 16.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else {
            Column(
                Modifier
                    .padding(pad)
                    .padding(16.dp)
            ) {
                Text("Baby Journals", style = MaterialTheme.typography.headlineSmall)
                Spacer(Modifier.height(16.dp))
                ui.babies.forEach { baby ->
                    ElevatedCard(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .clickable { onOpen(baby.id) }
                    ) {
                        Column(
                            Modifier.padding(12.dp)
                        ) {
                            Text(
                                baby.firstName + " " + baby.lastName,
                                style = MaterialTheme.typography.titleMedium
                            )
                            val age = formatAge(LocalDate.parse(baby.birthDate))
                            Text("Age: $age")
                            Text("Size: ${baby.heightCm} cm * Weight: ${baby.weightKg} kg")
                        }
                    }
                }
            }
        }
    }
}