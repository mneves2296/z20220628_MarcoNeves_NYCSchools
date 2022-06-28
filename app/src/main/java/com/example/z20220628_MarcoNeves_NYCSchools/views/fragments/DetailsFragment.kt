package com.example.z20220628_MarcoNeves_NYCSchools.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.z20220628_MarcoNeves_NYCSchools.R
import com.example.z20220628_MarcoNeves_NYCSchools.databinding.SchoolDetailsFragBinding
import com.example.z20220628_MarcoNeves_NYCSchools.model.SchoolData
import com.google.android.material.snackbar.Snackbar

class DetailsFragment: Fragment(R.layout.school_details_frag) {

    private lateinit var binding: SchoolDetailsFragBinding

    companion object {
        const val DETAILS_FRAG_TAG = "DETAILS_FRAGMENT"
        const val BUNDLE_TAG = "schoolBundle"
    }

    private lateinit var schoolsList: SchoolData

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Create a binding object to the layout
        binding = SchoolDetailsFragBinding.bind(view)
        schoolsList = requireArguments().getSerializable(BUNDLE_TAG) as SchoolData
        binding.mapDataToViews(schoolsList)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun displayError(view: View, errorMsg: String) =
        Snackbar.make(view, errorMsg, Snackbar.LENGTH_LONG).show()


    private fun SchoolDetailsFragBinding.mapDataToViews(school: SchoolData) {
        schoolNameTv.text = school.tvSchool
        totalStudentsValue.text = this@DetailsFragment.context?.getString(
            R.string.total_students,
            school.tvTotalStudents ?: "?"
        )
        mathTV.text = school.tvMath
        readingTV.text = school.tvReading
        writingTv.text = school.tvWriting
    }
}