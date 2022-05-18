package com.example.android.rickmorty.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.android.rickmorty.data.local.CharacterInfoDatabase
import com.example.android.rickandmorty.data.local.entity.CharacterInfoRemoteKeysEntity
import com.example.android.rickandmorty.data.local.entity.ResultInfoEntity
import com.example.android.rickmorty.data.remote.RickAndMortyApi

@OptIn(ExperimentalPagingApi::class)
class RickAndMortyRemoteMediator(
    private val api: RickAndMortyApi,
    private val db: CharacterInfoDatabase
) : RemoteMediator<Int, ResultInfoEntity>() {

    private val characterInfoDao = db.resultInfoDao
    private val characterInfoRemoteKeysDao = db.characterInfoRemoteKeysDao

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ResultInfoEntity>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = api.getAllCharacters(page = currentPage)
            val endOfPaginationReached = response.results.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    characterInfoDao.clearResultInfo()
                    characterInfoRemoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = response.results.map { resultDto ->
                    CharacterInfoRemoteKeysEntity(
                        id = resultDto.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                val characterResponse = response.results.map { resultDto ->
                    ResultInfoEntity(
                        created = resultDto.created,
                        episode = resultDto.episode,
                        gender = resultDto.gender,
                        id = resultDto.id,
                        image = resultDto.image,
                        location = resultDto.location.toLocation(),
                        characterName = resultDto.name,
                        origin = resultDto.origin.toOrigin(),
                        species = resultDto.species,
                        status = resultDto.status,
                        type = resultDto.type,
                        characterUrl = resultDto.url
                    )
                }
                characterInfoRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                characterInfoDao.insertCharacterResultInfo(resultInfo = characterResponse)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, ResultInfoEntity>
    ): CharacterInfoRemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                characterInfoRemoteKeysDao.getRemoteKeys(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, ResultInfoEntity>
    ): CharacterInfoRemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { resultInfoEntity ->
                characterInfoRemoteKeysDao.getRemoteKeys(id = resultInfoEntity.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, ResultInfoEntity>
    ): CharacterInfoRemoteKeysEntity? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { resultInfoEntity ->
            characterInfoRemoteKeysDao.getRemoteKeys(id = resultInfoEntity.id)
        }
    }

}