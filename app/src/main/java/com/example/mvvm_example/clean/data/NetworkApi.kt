package com.example.mvvm_example.clean.data

import com.example.mvvm_example.clean.data.dto.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApi {
    @GET("/api/character")
    suspend fun getDashboardWidgets(@Query("page") id: Int): CharacterDto
}
