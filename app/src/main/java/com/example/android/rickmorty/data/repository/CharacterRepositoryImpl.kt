package com.example.android.rickmorty.data.repository

import androidx.paging.*
import com.example.android.rickmorty.data.local.CharacterInfoDatabase
import com.example.android.rickmorty.data.paging.RickAndMortyRemoteMediator
import com.example.android.rickmorty.data.paging.SearchPagingSource
import com.example.android.rickmorty.data.remote.RickAndMortyApi
import com.example.android.rickmorty.domain.model.ResultInfo
import com.example.android.rickmorty.domain.repository.CharacterRepository
import com.example.android.rickmorty.util.Constants.ITEMS_PER_PAGE
import com.example.android.rickmorty.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi,
    private val db: CharacterInfoDatabase
) : CharacterRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllCharacterList(): Flow<PagingData<ResultInfo>> {
        val pagingSourceFactory = { db.resultInfoDao.getAllResultInfo() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = RickAndMortyRemoteMediator(
                api = api,
                db = db
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
            .map { pagingData ->
                pagingData.map { resultInfoEntity ->
                    resultInfoEntity.toResultInfo()
                }
            }
    }

    override suspend fun getCharacterDetailById(id: Int): Resource<ResultInfo> {
        return try {
            val response = db.resultInfoDao.getResultInfoById(id = id)
            Resource.Success(response.toResultInfo())
        } catch (e: Exception) {
            Resource.Error(message = e.message ?: "Error")
        }
    }

    override suspend fun searchCharacter(name: String): Flow<PagingData<ResultInfo>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = { SearchPagingSource(api, name) }
        ).flow
            .map { pagingData->
                pagingData.map { it.toResultInfo() }
            }
    }
}