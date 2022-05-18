package com.example.android.rickmorty.data.remote

import com.example.android.rickandmorty.data.remote.dto.CharacterInfoDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): CharacterInfoDto

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}