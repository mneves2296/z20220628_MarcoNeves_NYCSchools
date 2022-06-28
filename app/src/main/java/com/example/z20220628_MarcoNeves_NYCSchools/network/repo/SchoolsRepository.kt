package com.example.z20220628_MarcoNeves_NYCSchools.network.repo

import com.example.z20220628_MarcoNeves_NYCSchools.model.SchoolDTO
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Dagger2 annotation-- used for runtime instance creation.
 */
@Singleton
class SchoolsRepository @Inject constructor() {
    suspend fun getSchools(): List<SchoolDTO> = RetrofitClient.getService().getSchoolsData()
}