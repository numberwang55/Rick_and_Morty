package com.example.android.rickmorty.data.remote.dto

import com.example.android.rickmorty.domain.model.CharacterInfo

data class CharacterInfoDto(
    val info: InfoDto,
    val results: List<ResultDto>
) {
    fun toCharacterInfo(): CharacterInfo {
        return CharacterInfo(
            info = info.toInfo(),
            results = results.map { it.toResult() }
        )
    }
}