package com.example.android.rickandmorty.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterInfoRemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)
