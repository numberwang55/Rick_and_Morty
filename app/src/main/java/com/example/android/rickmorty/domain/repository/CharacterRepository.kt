package com.example.android.rickmorty.domain.repository

import androidx.paging.PagingData
import com.example.android.rickmorty.domain.model.ResultInfo
import com.example.android.rickmorty.util.Resource
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getAllCharacterList(): Flow<PagingData<ResultInfo>>

    suspend fun getCharacterDetailById(id : Int): Resource<ResultInfo>

    suspend fun searchCharacter(name: String): Flow<PagingData<ResultInfo>>
}