package com.example.android.rickmorty.presentation.screens.character_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.android.rickmorty.domain.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterInfoViewModel @Inject constructor(
    private val repository: CharacterRepository
): ViewModel() {

    val getAllCharacterInfo =
        repository.getAllCharacterList().cachedIn(viewModelScope)

//    var state by mutableStateOf(CharacterInfoState())
//
//    val paginator = DefaultPaginator(
//        initialKey = state.page,
//        onLoadUpdated = {
//            state = state.copy(isLoading = it)
//        },
//        onRequest = { nextKey ->
//            repository.getCharacterList(nextKey)
//        },
//        getNextKey = {
//            state.page + 1
//        },
//        onError = {
//            state = state.copy(error = it?.localizedMessage)
//        },
//        onSuccess = { items, newKey ->
//            state = state.copy(
//                items = state.items + items.results,
//                page = newKey,
//                endReached = items.results.isEmpty()
//            )
//        }
//    )
//
//    init {
//        getAllCharacters()
//    }
//
//    fun getAllCharacters() {
//        viewModelScope.launch {
//            paginator.getAllCharacters()
//        }
//    }
}