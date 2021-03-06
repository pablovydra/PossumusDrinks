package com.vydra.possumusdrinks.drinks.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://www.thecocktaildb.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface DrinksApiService {

    // FILTER SEARCH BY NAME
    @GET("/api/json/v1/1/search.php")
    fun getProperties(
        @Query("s") s: String
    ):
            Deferred<DrinksResponse>

    // FILTER SEARCH BY INGREDIENT
    @GET("/api/json/v1/1/filter.php")
    fun getProperties2(
        @Query("i") i: String
    ):
            Deferred<DrinksResponse>
}

object DrinksApi {
    val retrofitService : DrinksApiService by lazy { retrofit.create(DrinksApiService::class.java) }
}