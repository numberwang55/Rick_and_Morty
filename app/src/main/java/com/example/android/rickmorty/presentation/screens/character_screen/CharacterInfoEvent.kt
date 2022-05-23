package com.example.android.rickmorty.presentation.screens.character_screen

sealed class CharacterInfoEvent {
    data class OnSearchQueryChange(val query: String): CharacterInfoEvent()
}
