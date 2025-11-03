package com.example.baby_journals.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.baby_journals.data.Baby
import com.example.baby_journals.model.AppJson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.builtins.ListSerializer

private val Context.dataStore by preferencesDataStore(name = "baby_journals")

class BabyLocalData(private val context: Context) {
    private val KEY = stringPreferencesKey("babies_json")

    val babies: Flow<List<Baby>> = context.dataStore.data.map { prefs ->
        val json = prefs[KEY].orEmpty()
        if (json.isBlank()) emptyList()
        else runCatching { AppJson.decodeFromString(ListSerializer(Baby.serializer()), json) }
            .getOrElse { emptyList() }
    }

    suspend fun save(all: List<Baby>){
        val json = AppJson.encodeToString(ListSerializer(Baby.serializer()), all)
        context.dataStore.edit { it[KEY] = json }
    }
}