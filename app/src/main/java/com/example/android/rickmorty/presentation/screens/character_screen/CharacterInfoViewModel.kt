package com.example.android.rickmorty.presentation.screens.character_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.android.rickmorty.domain.model.ResultInfo
import com.example.android.rickmorty.domain.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterInfoViewModel @Inject constructor(
    private val repository: CharacterRepository
): ViewModel() {

    val getAllCharacterInfo =
        repository.getAllCharacterList().cachedIn(viewModelScope)

    var state by mutableStateOf(CharacterInfoState())
    private var searchJob: Job? = null

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _searchedImages = MutableStateFlow<PagingData<ResultInfo>>(PagingData.empty())
    val searchedImages = _searchedImages

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

//    fun onEvent(event: CharacterInfoEvent) {
//        when(event) {
//            is CharacterInfoEvent.OnSearchQueryChange -> {
//                state = state.copy(query = event.query)
//                searchJob?.cancel()
//                searchJob = viewModelScope.launch {
//                    delay(2000L)
//                    searchCharacter()
//                }
//            }
//        }
//    }

    fun searchCharacter(name: String = _searchQuery.value) {
        if (name.isNotEmpty()){
            viewModelScope.launch {
                repository.searchCharacter(name).cachedIn(viewModelScope).collect {
                    _searchedImages.value = it
                }
            }
        }
    }

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