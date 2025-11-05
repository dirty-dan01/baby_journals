package com.example.baby_journals.ui.screens

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.baby_journals.ui.components.AboveLabel
import com.example.baby_journals.ui.components.TextFieldStyle
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddBabyScreen(
    onSave: (String, String, LocalDate, Double, Double, String?, String?, String?) -> Unit,
    onCancel: () -> Unit
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(LocalDate.now()) }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var blood by remember { mutableStateOf("") }
    var allergies by remember { mutableStateOf("") }
    val ctx = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFC90B4),
                        Color.White
                    )
                )
            )
    ) {
        Column(
            Modifier.padding(16.dp)
        ) {
            AboveLabel("Baby's First Name")
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = firstName,
                onValueChange = { firstName = it },
                colors = TextFieldStyle.WhiteNoLine(),
                label = {
                    Text("Enter Name", color = Color.Gray)
                }
            )
            Spacer(Modifier.height(8.dp))
            AboveLabel("Baby's Last Name")
            OutlinedTextField(
                value = lastName,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { lastName = it },
                colors = TextFieldStyle.WhiteNoLine(),
                label = {
                    Text("Enter Name", color = Color.Gray)
                }
            )
            Spacer(Modifier.height(8.dp))
            AboveLabel("Baby's Date of Birthday")
            OutlinedTextField(
                value = date.toString(),
                onValueChange = {},
                readOnly = true,
                colors = TextFieldStyle.WhiteNoLine(),
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
            AboveLabel("Baby's Birth Height")
            OutlinedTextField(
                value = height,
                onValueChange = { height = it },
                colors = TextFieldStyle.WhiteNoLine(),
                label = {
                    Text(
                        "Height (cm)",
                        color = Color.Gray
                    )
                }
            )
            Spacer(Modifier.height(8.dp))
            AboveLabel("Baby's Birth Weight")
            OutlinedTextField(
                value = weight,
                onValueChange = { weight = it },
                colors = TextFieldStyle.WhiteNoLine(),
                label = { Text("Weight (kg)", color = Color.Gray) }
            )
            Spacer(Modifier.height(8.dp))
            AboveLabel("Baby's Blood Type")
            OutlinedTextField(
                value = blood,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { blood = it },
                colors = TextFieldStyle.WhiteNoLine(),
                label = { Text("Select Blood Type", color = Color.Gray) }
            )
            Spacer(Modifier.height(8.dp))
            AboveLabel("Allergies")
            OutlinedTextField(
                value = allergies,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { allergies = it },
                colors = TextFieldStyle.WhiteNoLine(),
                label = { Text("Enter Allergies", color = Color.Gray) }
            )
            Spacer(Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = {
                        onSave(
                            firstName,
                            lastName,
                            date,
                            height.toDouble(),
                            weight.toDouble(),
                            blood.ifBlank { null },
                            null,
                            allergies.ifBlank { null }
                        )
                    },
                    enabled = firstName.isNotBlank()
                            && height.toDoubleOrNull() != null
                            && weight.toDoubleOrNull() != null
                ) {
                    Text("Save")
                }
                OutlinedButton(onClick = onCancel) { Text("Cancel") }
            }
        }
    }
}