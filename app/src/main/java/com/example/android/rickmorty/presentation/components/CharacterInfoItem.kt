package com.example.android.rickmorty.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.android.rickmorty.domain.model.ResultInfo

@Composable
fun CharacterInfoItem(
    entry: ResultInfo,
    modifier: Modifier = Modifier,
    onPokemonClick: (Int) -> Unit
) {
    var isImageLoading by remember { mutableStateOf(true) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .background(Color.LightGray)
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .clickable {
                onPokemonClick(entry.id)
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (isImageLoading)
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.scale(0.5f)
                )
            AsyncImage(
                model = entry.image,
                contentDescription = "Image",
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally),
                onSuccess = { isImageLoading = false }
            )
            if (!isImageLoading) {
                Text(
                    text = entry.name,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


