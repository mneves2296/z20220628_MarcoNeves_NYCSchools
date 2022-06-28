package com.example.z20220628_MarcoNeves_NYCSchools

import android.app.Application
import android.os.StrictMode
import com.example.z20220628_MarcoNeves_NYCSchools.di.ApplicationComponent
import com.example.z20220628_MarcoNeves_NYCSchools.di.DaggerApplicationComponent

class App: Application() {
    val component: ApplicationComponent = DaggerApplicationComponent.create()
    override fun onCreate() {
        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .build()
        )
        super.onCreate()
    }
}
