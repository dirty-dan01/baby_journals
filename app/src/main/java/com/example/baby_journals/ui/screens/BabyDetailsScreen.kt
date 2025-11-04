package com.example.baby_journals.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.baby_journals.ui.BabyListUiState
import com.example.baby_journals.utils.formatAge
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BabyDetailsScreen(
    id: String,
    state: StateFlow<BabyListUiState>,
    onBack: () -> Unit
){
    val ui by state.collectAsState()
    val baby = ui.babies.firstOrNull{it.id == id}

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(baby?.name ?: "Detail") },
            navigationIcon = { TextButton(onClick = onBack) {Text("Back")}
            }) }
    ) { pad ->
        if (baby == null){
            Box(Modifier.padding(pad).padding(16.dp)){
                Text("Not found")}
            return@Scaffold
        }
        Column(
            Modifier.padding(pad).padding(16.dp).fillMaxWidth()
        ) {
            baby.photoUr?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = "Baby photo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.height(12.dp))
            }
            val age = formatAge(LocalDate.parse(baby.birthDate))
            Text("Age: $age")
            Text("Blood type: ${baby.bloodType} ?: Unknown")
            Text("Height: ${baby.heightCm} cm")
            Text("Weight: ${baby.weightKg} kg")
            baby.notes?.takeIf { it.isNotBlank() }?.let {
                Spacer(Modifier.height(8.dp)); Text("Notes: $it")
            }
        }
    }
}