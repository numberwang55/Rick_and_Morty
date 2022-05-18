package com.example.android.rickmorty.domain.model

import com.example.android.rickmorty.domain.model.Info
import com.example.android.rickmorty.domain.model.ResultInfo

data class CharacterInfo(
    val id: Int? = null,
    val info: Info,
    val results: List<ResultInfo>
)
