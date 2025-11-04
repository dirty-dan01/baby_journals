package com.example.baby_journals.ui.screens

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddBabyScreen(
    onSave: (String, LocalDate, Double, Double, String?, String?, String?) -> Unit,
    onCancel: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(LocalDate.now()) }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var blood by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    val ctx = LocalContext.current

    Column(
        Modifier.padding(16.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = {
                Text("Name")
            }
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = date.toString(),
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    DatePickerDialog(
                        ctx, { _, year, month, d -> date = LocalDate.of(year, month + 1, d) },
                        date.year,
                        date.monthValue - 1,
                        date.dayOfMonth
                    ).show()
                } then (Modifier)
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = {
                Text(
                    "Height (cm)"
                )
            }
        )
        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight (kg)") }
        )
        OutlinedTextField(
            value = blood,
            onValueChange = { blood = it },
            label = { Text("Blood type (optional)") }
        )
        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notes (optional)") }
        )
        Spacer(Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                onClick = {
                    onSave(
                        name,
                        date,
                        height.toDouble(),
                        weight.toDouble(),
                        blood.ifBlank { null },
                        null,
                        notes.ifBlank { null }
                    )
                },
                enabled = name.isNotBlank()
                        && height.toDoubleOrNull() != null
                        && weight.toDoubleOrNull() != null
            ) {

            }.also {
                Text("Save")
            }
            OutlinedButton(onClick = onCancel) { Text("Cancel") }
        }

    }
}