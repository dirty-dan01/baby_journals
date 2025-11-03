package com.example.baby_journals.data.repo

import com.example.baby_journals.data.Baby
import kotlinx.coroutines.flow.Flow

interface BabyRepository {
    val babies: Flow<List<Baby>>
    suspend fun add(baby: Baby)
    suspend fun get(id: String): Baby?
    suspend fun update(baby: Baby)
    suspend fun remove(id: String)
}