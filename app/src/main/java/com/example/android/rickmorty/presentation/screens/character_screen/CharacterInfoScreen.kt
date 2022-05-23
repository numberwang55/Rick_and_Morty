package com.example.android.rickmorty.presentation.screens.character_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.filter
import coil.compose.AsyncImage
import com.example.android.rickmorty.R
import com.example.android.rickmorty.domain.model.ResultInfo
import com.example.android.rickmorty.presentation.components.CharacterInfoItem
import com.example.android.rickmorty.presentation.components.CharacterInfoRow
import com.example.android.rickmorty.presentation.screens.destinations.DetailScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination(start = true)
@Composable
fun CharacterInfoScreen(
    navigator: DestinationsNavigator,
    viewModel: CharacterInfoViewModel = hiltViewModel()
) {
    val getCharInfo = viewModel.getAllCharacterInfo.collectAsLazyPagingItems()
    val state = viewModel.state
    val searchedCharacter = viewModel.searchedImages.collectAsLazyPagingItems()

    Scaffold(backgroundColor = Color.Black.copy(alpha = 0.5f)) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.rick_and_morty_logo
                ),
                contentDescription = "logo",
                modifier = Modifier.background(Color.Black)
            )
            OutlinedTextField(
                value = viewModel.searchQuery.value,
                onValueChange = { newQuery ->
                    viewModel.updateSearchQuery(newQuery)
                },
                keyboardActions = KeyboardActions(
                    onSearch = {
                        viewModel.searchCharacter()
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                )
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
            ) {
                if (viewModel.searchQuery.value.isNotEmpty()) {
                    items(searchedCharacter.itemCount) { i ->
                        Column(Modifier.padding(8.dp)) {
                            searchedCharacter[i]?.let {
                                CharacterInfoItem(
                                    entry = it,
                                    onPokemonClick = { id ->
                                        navigator.navigate(DetailScreenDestination(id))
                                    }
                                )
                            }
                        }
                    }
                } else {
                    items(getCharInfo.itemCount) { i ->
                        Column(Modifier.padding(8.dp)) {
                            getCharInfo[i]?.let { resultInfo ->
                                CharacterInfoItem(
                                    entry = resultInfo,
                                    onPokemonClick = { id ->
                                        navigator.navigate(DetailScreenDestination(id))
                                    }
                                )
                            }
                        }
                    }
                }
            }

//            LazyColumn() {
//                val itemCount = if (getCharInfo.itemCount % 2 == 0) {
//                    getCharInfo.itemCount / 2
//                } else {
//                    getCharInfo.itemCount / 2 + 1
//                }
//                items(itemCount) { i ->
//                    CharacterInfoRow(
//                        rowIndex = i,
//                        entries = getCharInfo,
//                        onPokemonClick = { id ->
//                            navigator.navigate(DetailScreenDestination(id))
//                        }
//                    )
//                }
//            }
        }
    }
}

//    val state = viewModel.state
//    val lazyListScrollState = rememberLazyGridState()
//    val endReached = remember {
//        derivedStateOf {
//            val lastVisibleItem = lazyListScrollState.layoutInfo.visibleItemsInfo.lastOrNull()
//                ?: return@derivedStateOf true
//            lastVisibleItem.index == lazyListScrollState.layoutInfo.totalItemsCount - 1
//        }
//    }
//    LaunchedEffect(key1 = endReached.value) {
//        viewModel.getAllCharacters()
//    }
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(3),
//        state = lazyListScrollState,
//        modifier = Modifier.fillMaxSize()
//    ) {
//        items(state.items) { item ->
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp)
//            ) {
//                Text(text = item.name)
////                Spacer(modifier = Modifier.height(8.dp))
//                Text(text = item.species)
//                AsyncImage(
//                    model = item.image,
//                    contentDescription = "Image"
//                )
//            }
//        }
//        item {
//            Row(
//                Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center
//            ) {
//                if (state.isLoading) {
//                    CircularProgressIndicator()
//                }
//            }
//        }
//    }