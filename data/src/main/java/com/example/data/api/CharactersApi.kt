package com.example.data.api


import com.example.data.models.Characters
import com.example.data.models.Details
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {

    @GET("character")
    suspend fun getAllCharacters(@Query("page") page: Int): Response<Characters>

    @GET("character/{id}")
    suspend fun getDetailsById(@Path("id") id: Int): Response<Details>

    @GET("character")
    suspend fun searchByName(@Query("name") name: String): Response<Characters>

}