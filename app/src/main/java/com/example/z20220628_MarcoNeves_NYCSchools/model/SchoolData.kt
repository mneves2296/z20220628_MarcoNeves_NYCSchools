package com.example.z20220628_MarcoNeves_NYCSchools.model

import androidx.annotation.DrawableRes
import java.io.Serializable

/**
 * Serializable object with can be bundled and passed on to
 * [com.example.z20220628_MarcoNeves_NYCSchools.views.fragments.DetailsFragment]
 */
data class SchoolData(
    @DrawableRes val schoolLogo: Int? = null,
    val tvSchool:String? = null,
    val tvTotalStudents: String? = null,
    val tvMath: String? = null,
    val tvWriting: String? = null,
    val tvReading: String? = null,
): Serializable
