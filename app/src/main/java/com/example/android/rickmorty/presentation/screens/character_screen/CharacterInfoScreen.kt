package com.example.android.rickmorty.presentation.screens.character_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.android.destinations.DetailScreenDestination
import com.example.android.rickmorty.R
import com.example.android.rickmorty.presentation.components.CharacterInfoItem
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

    Scaffold(backgroundColor = Color.Black.copy(alpha = 0.5f)) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.rick_and_morty_logo
                ),
                contentDescription = "logo",
                modifier = Modifier.background(Color.Black)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
            ) {
                items(getCharInfo.itemCount) { i ->
                    Column(Modifier.padding(8.dp)) {
                        CharacterInfoItem(
                            entry = getCharInfo[i]!!,
                            onPokemonClick = { id ->
                                navigator.navigate(DetailScreenDestination(id))
                            }
                        )
                    }
                }
            }
        }
    }
}

//            LazyColumn() {
//                val itemCount = if (getCharInfo.itemCount % 2 == 0){
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