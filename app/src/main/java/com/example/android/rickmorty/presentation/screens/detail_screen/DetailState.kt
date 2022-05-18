package com.example.android.rickmorty.presentation.screens.detail_screen

import com.example.android.rickmorty.domain.model.ResultInfo

data class DetailState(
    val characterDetail: ResultInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
