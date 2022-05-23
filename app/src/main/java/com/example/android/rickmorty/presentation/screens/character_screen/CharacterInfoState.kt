package com.example.android.rickmorty.presentation.screens.character_screen

import androidx.paging.PagingData
import com.example.android.rickmorty.domain.model.ResultInfo

data class CharacterInfoState(
    val isLoading: Boolean = false,
    val items: PagingData<ResultInfo> = PagingData.empty(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 1,
    val query: String = ""
)
