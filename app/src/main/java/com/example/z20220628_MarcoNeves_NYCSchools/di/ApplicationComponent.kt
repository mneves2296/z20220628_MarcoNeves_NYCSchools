package com.example.z20220628_MarcoNeves_NYCSchools.di

import com.example.z20220628_MarcoNeves_NYCSchools.views.activities.MainActivity
import com.example.z20220628_MarcoNeves_NYCSchools.network.repo.SchoolsRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Configure the Dagger2 application graph.
 */
@Singleton
@Component( modules = [])
interface ApplicationComponent{
    fun getRepository(): SchoolsRepository
    fun inject(activity: MainActivity)
}
