package com.example.android.rickmorty.presentation.screens.character_screen

import com.example.android.rickmorty.domain.model.ResultInfo

data class CharacterInfoState(
    val isLoading: Boolean = false,
    val items: List<ResultInfo> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 1
)
