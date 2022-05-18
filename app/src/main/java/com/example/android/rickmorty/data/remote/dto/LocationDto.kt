package com.example.android.rickandmorty.data.remote.dto

import com.example.android.rickmorty.domain.model.Location

data class LocationDto(
    val name: String,
    val url: String
) {
    fun toLocation(): Location {
        return Location(
            locationName = name,
            locationUrl = url
        )
    }
}