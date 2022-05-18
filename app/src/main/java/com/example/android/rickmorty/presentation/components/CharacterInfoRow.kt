package com.example.android.rickmorty.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.android.rickmorty.domain.model.ResultInfo

@Composable
fun CharacterInfoRow(
    rowIndex: Int,
    entries: LazyPagingItems<ResultInfo>,
    onPokemonClick: (Int) -> Unit
) {
    Column(modifier = Modifier.padding(top = 16.dp)) {
        if (entries.itemSnapshotList.items.isNotEmpty()) {
            Row {
                entries.get(rowIndex * 2)?.let {
                    CharacterInfoItem(
                        entry = it,
                        onPokemonClick = { name ->
                            onPokemonClick(name)
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                entries.get(rowIndex * 2 + 1)?.let {
                    CharacterInfoItem(
                        entry = it,
                        onPokemonClick = { name ->
                            onPokemonClick(name)
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
//            Spacer(modifier = Modifier.height(16.dp))
        } else {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun RetrySection(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(error, color = Color.Red, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(text = "Retry")
        }
    }
}