package com.example.z20220628_MarcoNeves_NYCSchools.views.activities

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.z20220628_MarcoNeves_NYCSchools.App
import com.example.z20220628_MarcoNeves_NYCSchools.R
import com.example.z20220628_MarcoNeves_NYCSchools.adapter.NycSchoolsAdapter
import com.example.z20220628_MarcoNeves_NYCSchools.databinding.ActivityMainBinding
import com.example.z20220628_MarcoNeves_NYCSchools.model.NetworkResult
import com.example.z20220628_MarcoNeves_NYCSchools.model.SchoolDTO
import com.example.z20220628_MarcoNeves_NYCSchools.network.repo.SchoolsRepository
import com.example.z20220628_MarcoNeves_NYCSchools.views.fragments.DetailsFragment
import com.example.z20220628_MarcoNeves_NYCSchools.views.fragments.DetailsFragment.Companion.BUNDLE_TAG
import com.example.z20220628_MarcoNeves_NYCSchools.views.fragments.DetailsFragment.Companion.DETAILS_FRAG_TAG
import com.example.z20220628_MarcoNeves_NYCSchools.util.mapSchoolDto
import com.example.z20220628_MarcoNeves_NYCSchools.viewmodel.SharedViewModel
import com.example.z20220628_MarcoNeves_NYCSchools.viewmodel.factory.SchoolViewModelFactory
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var schoolRepo: SchoolsRepository
    private lateinit var schoolRV: RecyclerView
    private lateinit var schoolSearch: SearchView

    /** I'm choosing to initialize [viewModelFactory] as well as
     * [viewModel] by lazy here as these objects are expensive and
     * they need only be created in memory once they are ready to be used. **/
    private val viewModelFactory by lazy {
        SchoolViewModelFactory(schoolRepo)
    }
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(SharedViewModel::class.java)
    }

    private val schoolAdapter: NycSchoolsAdapter = NycSchoolsAdapter(
        object: NycSchoolsAdapter.OnSchoolSelected {
            override fun openDetailsFragment(currentSchool: SchoolDTO) {
                val schoolData = mapSchoolDto(currentSchool)
                val detailsFragBundle = Bundle().apply {
                    putSerializable(BUNDLE_TAG, schoolData)
                }
                val myDetailsFragment = DetailsFragment()
                myDetailsFragment.arguments = detailsFragBundle
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, myDetailsFragment, DETAILS_FRAG_TAG)
                    .commit()
        }
    }, this@MainActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (applicationContext as App).component.inject(this)
        setSchoolAdapter()
        // begin observing for values from the VM.
        setSchoolObserver()
        // actually make the call to fetch values.
        viewModel.fetchSchoolData()
        // begin listening for manual searches (keyed in)
        setSearchListener()
    }

    private fun setSchoolAdapter() {
        schoolRV = binding.schoolRv
        schoolRV.apply {
            layoutManager = GridLayoutManager(
                this@MainActivity,
                1
            )
        }
        schoolRV.adapter = schoolAdapter
    }

    private fun setSearchListener() {
        schoolSearch = binding.schoolSearch.also {
            it.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean = true
                override fun onQueryTextChange(newText: String): Boolean {
                    schoolAdapter.search(newText)
                    return true
                }
            })
        }
    }

    private fun setSchoolObserver() {
        viewModel.schoolLiveData.observe(this) {
            when (it) {
                is NetworkResult.Success -> schoolAdapter.schoolDTOS = it.schoolList
                is NetworkResult.Failure -> displayError(it.error)
            }
        }
    }

    private fun displayError(error: String) =
        Snackbar.make(findViewById(android.R.id.content), error, Snackbar.LENGTH_LONG).show()

    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}