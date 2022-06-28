package com.example.z20220628_MarcoNeves_NYCSchools.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.z20220628_MarcoNeves_NYCSchools.network.repo.SchoolsRepository

class SchoolViewModelFactory(private val schoolRepo: SchoolsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(SchoolsRepository::class.java)
            .newInstance(schoolRepo)
    }
}
