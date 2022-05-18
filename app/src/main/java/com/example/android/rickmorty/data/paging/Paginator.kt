package com.example.android.rickmorty.data.paging

interface Paginator<Key, Item> {
    suspend fun getAllCharacters()
    fun reset()
}