package com.example.android.rickmorty.data.remote.dto

import com.example.android.rickmorty.data.local.entity.ResultInfoEntity
import com.example.android.rickmorty.domain.model.ResultInfo

data class ResultDto(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationDto,
    val name: String,
    val origin: OriginDto,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) {
    fun toResultInfoEntity(): ResultInfoEntity {
        return ResultInfoEntity(
            created = created,
            episode = episode,
            gender = gender,
            id = id,
            image = image,
            location = location.toLocation(),
            characterName = name,
            origin = origin.toOrigin(),
            species = species,
            status = status,
            type = type,
            characterUrl = url
        )
    }
    fun toResult(): ResultInfo {
        return ResultInfo(
            created = created,
            episode = episode,
            gender = gender,
            id = id,
            image = image,
            location = location.toLocation(),
            name = name,
            origin = origin.toOrigin(),
            species = species,
            status = status,
            type = type,
            url = url
        )
    }
}