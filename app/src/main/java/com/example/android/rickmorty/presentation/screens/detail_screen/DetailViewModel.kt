package com.example.android.rickmorty.presentation.screens.detail_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.rickmorty.domain.repository.CharacterRepository
import com.example.android.rickmorty.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: CharacterRepository
): ViewModel() {

    var state by mutableStateOf(DetailState())
    var id by mutableStateOf(0)

    init {
        val characterId = savedStateHandle.get<Int>("id")
        characterId?.let {
            getCharacterDetailsById(characterId)
        }
    }

    private fun getCharacterDetailsById(id: Int) {
        viewModelScope.launch {
            when (val result = repository.getCharacterDetailById(id)) {
                is Resource.Success -> {
                    state = state.copy(
                        characterDetail = result.data
                    )
                }
                is Resource.Error -> {
                    state = state.copy(error = result.message)
                }
                is Resource.Loading -> {}
            }
        }
    }

}