package com.example.z20220628_MarcoNeves_NYCSchools.model

import java.io.Serializable

/**
 * DTO Model class to represent the a single schools' data.
 */
data class SchoolDTO (
    val school_name: String,
    val num_of_sat_test_takers: String,
    val sat_critical_reading_avg_score: String,
    val sat_math_avg_score: String,
    val sat_writing_avg_score: String
): Serializable