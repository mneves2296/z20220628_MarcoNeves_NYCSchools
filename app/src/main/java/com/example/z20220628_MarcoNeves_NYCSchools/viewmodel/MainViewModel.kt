package com.example.z20220628_MarcoNeves_NYCSchools.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.z20220628_MarcoNeves_NYCSchools.model.NetworkResult
import com.example.z20220628_MarcoNeves_NYCSchools.network.repo.SchoolsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * This ViewModel serves to host the LiveData objects which contain the school's data.
 * Once new data is received, we then update the UI by observing this data from the
 * Activity/Fragment.
 */
class SharedViewModel(
    private val repo: SchoolsRepository
): ViewModel() {

    private val _schoolLiveData = MutableLiveData<NetworkResult>()
    val schoolLiveData: LiveData<NetworkResult>
        get() = _schoolLiveData

    /**
     * Attempt to fetch the list of schools using
     * the CoroutineDispatcher [Dispatchers.IO]-- which is designed for
     * offloading blocking IO tasks to a shared pool of threads.
     */
    fun fetchSchoolData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _schoolLiveData.postValue(NetworkResult.Success(repo.getSchools()))
            } catch(e: Exception){
                _schoolLiveData.postValue(NetworkResult.Failure(e.message?:e.localizedMessage))
            }
        }
    }
}