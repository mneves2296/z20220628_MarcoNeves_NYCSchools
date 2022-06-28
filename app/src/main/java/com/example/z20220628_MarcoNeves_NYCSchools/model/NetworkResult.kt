package com.example.z20220628_MarcoNeves_NYCSchools.model

/**
 * State class to represent the different possibilities
 * [success or failure] at any point in time.
 */
sealed class NetworkResult {
    data class Success(val schoolList: List<SchoolDTO>) : NetworkResult()
    data class Failure(val error: String) : NetworkResult()
}