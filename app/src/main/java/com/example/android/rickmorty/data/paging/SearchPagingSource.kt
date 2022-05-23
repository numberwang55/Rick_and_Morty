package com.example.android.rickmorty.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.android.rickmorty.data.local.entity.ResultInfoEntity
import com.example.android.rickmorty.data.remote.RickAndMortyApi

class SearchPagingSource(
    private val api: RickAndMortyApi,
    private val name: String
): PagingSource<Int, ResultInfoEntity>() {

    override fun getRefreshKey(state: PagingState<Int, ResultInfoEntity>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultInfoEntity> {
        val currentPage = params.key ?: 1
        return try {
            val response = api.getCharacterByName(name = name, page = currentPage)
            val endOfPaginationReached = response.results.isEmpty()
            if (response.results.isNotEmpty()) {
                LoadResult.Page(
                    data = response.results.map { it.toResultInfoEntity() },
                    prevKey = if (currentPage == 1) null else currentPage -1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}