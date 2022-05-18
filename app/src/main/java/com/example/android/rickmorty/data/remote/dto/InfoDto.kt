package com.example.android.rickandmorty.data.remote.dto

import com.example.android.rickmorty.domain.model.Info

data class InfoDto(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: String? = null
) {
    fun toInfo(): Info {
        return Info(
            count = count,
            next = next,
            pages = pages,
            prev = prev
        )
    }
}