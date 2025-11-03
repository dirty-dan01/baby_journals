package com.example.baby_journals.model

import kotlinx.serialization.json.Json


val AppJson = Json{
    ignoreUnknownKeys = true
    prettyPrint = false
    encodeDefaults = true
}