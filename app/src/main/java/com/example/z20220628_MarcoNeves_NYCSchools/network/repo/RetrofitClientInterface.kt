package com.example.z20220628_MarcoNeves_NYCSchools.network.repo

import com.example.z20220628_MarcoNeves_NYCSchools.model.SchoolDTO
import retrofit2.http.GET

/**
 * This is the interface for performing GET requests.
 */
interface RetrofitClientInterface {
    @GET("resource/f9bf-2cp4.json")
    suspend fun getSchoolsData(): List<SchoolDTO>
}