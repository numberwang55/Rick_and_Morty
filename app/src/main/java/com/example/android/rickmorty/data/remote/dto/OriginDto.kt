package com.example.android.rickandmorty.data.remote.dto

import com.example.android.rickmorty.domain.model.Origin

data class OriginDto(
    val name: String,
    val url: String
) {
    fun toOrigin(): Origin {
        return Origin(
            originName = name,
            originUrl = url
        )
    }
}