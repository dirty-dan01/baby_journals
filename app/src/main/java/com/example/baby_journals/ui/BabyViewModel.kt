package com.example.baby_journals.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baby_journals.data.Baby
import com.example.baby_journals.data.repo.BabyRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID


data class BabyListUiState(
    val babies: List<Baby> = emptyList()
)

class BabyViewModel(
    private val repo: BabyRepository
) : ViewModel() {
    val state: StateFlow<BabyListUiState> =
        repo.babies
            .map { BabyListUiState(it) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), BabyListUiState())

    fun addBaby(
        firstName: String,
        lastName: String,
        birthDate: LocalDate,
        heightCm: Double,
        weightKg: Double,
        bloodType: String? = null,
        photoUrl: String? = null,
        allergies: String? = null
    ) = viewModelScope.launch {
        val baby = Baby(
            id = UUID.randomUUID().toString(),
            firstName = firstName,
            lastName = lastName,
            birthDate = birthDate.toString(),
            heightCm = heightCm,
            weightKg = weightKg,
            bloodType = bloodType,
            photoUr = photoUrl,
            allergies = allergies,
        )
        repo.add(baby)
    }
}