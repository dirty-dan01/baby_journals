package com.example.baby_journals.data

import kotlinx.serialization.Serializable

@Serializable
data class Baby(
    val id: String,
    val name: String,
    val birthDate: String,
    val heightCm: Double,
    val weightKg: Double,
    val bloodType: String? = null,
    val photoUr: String? = null,
    val notes: String? = null,
)
