package com.example.z20220628_MarcoNeves_NYCSchools.util

import android.util.Log
import com.example.z20220628_MarcoNeves_NYCSchools.R
import com.example.z20220628_MarcoNeves_NYCSchools.model.SchoolDTO
import com.example.z20220628_MarcoNeves_NYCSchools.model.SchoolData

/**
 * Utility function to map out the DTO object into a
 * serializable object which can be Bundled and passed onto
 * other activities/fragments.
 */
fun mapSchoolDto(schoolDTO: SchoolDTO): SchoolData {
    var mySchoolData: SchoolData
    try {
        mySchoolData =  SchoolData(
            schoolLogo = R.drawable.ic_school_logo,
            tvTotalStudents = schoolDTO.num_of_sat_test_takers,
            tvMath = schoolDTO.sat_math_avg_score,
            tvReading = schoolDTO.sat_critical_reading_avg_score,
            tvWriting = schoolDTO.sat_writing_avg_score,
            tvSchool = schoolDTO.school_name
        )
    } catch(e: Exception) {
        // assigning default values
        mySchoolData = SchoolData()
        Log.e("MAPPING_ERROR", "Could not map DTO.")
    }
    return mySchoolData
}