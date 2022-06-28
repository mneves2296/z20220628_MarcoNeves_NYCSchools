package com.example.z20220628_MarcoNeves_NYCSchools.network.repo

import com.example.z20220628_MarcoNeves_NYCSchools.model.SchoolDTO
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This class is responsible for creating service provider-- which in this case
 * allows us to retrieve the list of [SchoolDTO] through the [RetrofitClientInterface]
 * instance.
 */

const val BASE_URL ="https://data.cityofnewyork.us/"

class RetrofitClient {
    companion object {
        private val interceptor = HttpLoggingInterceptor().also {
            it.setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        // this is the "factory" for calls
        private val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        // configure the interface with our info
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        // exposed function for fetching DTO
        fun getService(): RetrofitClientInterface {
            return retrofit.create(RetrofitClientInterface::class.java)
        }
    }
}