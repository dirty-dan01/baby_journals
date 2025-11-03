package com.example.baby_journals.data.repo

import com.example.baby_journals.data.Baby
import com.example.baby_journals.data.local.BabyLocalData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class BabyRepositoryImpl(
    private val local: BabyLocalData
): BabyRepository {

    override val babies: Flow<List<Baby>> = local.babies

    override suspend fun add(baby: Baby) {
        val list = local.babies.first().toMutableList()
        list.add(baby)
        local.save(list)
    }

    override suspend fun get(id: String): Baby? =
        local.babies.first().firstOrNull{ it.id == id}

    override suspend fun update(baby: Baby) {
        val list = local.babies.first().map { if (it.id == baby.id) baby else it}
    }

    override suspend fun remove(id: String) {
        val list = local.babies.first().filterNot { it.id == id }
    }

}