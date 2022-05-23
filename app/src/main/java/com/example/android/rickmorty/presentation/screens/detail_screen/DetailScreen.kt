package com.example.android.rickmorty.presentation.screens.detail_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun DetailScreen(
    id: Int,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.characterDetail
    state?.let {
        Scaffold() {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
                item {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 16.dp)
                    ) {
                        AsyncImage(
                            model = state.image,
                            contentDescription = "image",
                            modifier = Modifier
                                .size(300.dp)
                                .shadow(5.dp, RoundedCornerShape(25.dp))
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .shadow(5.dp, RoundedCornerShape(10.dp))
                                .background(Color.LightGray)
                                .padding(16.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Column {
                                Text(text = "Name: ${state.name}")
                                Text(text = "Gender: ${state.gender}")
                                Text(text = "Species: ${state.species}")
                                if (state.type.isNotBlank()) {
                                    Text(text = "Type: ${state.type}")
                                }
                                Text(text = "Origin: ${state.origin.originName.capitalize()}")
                                Text(
                                    text = "Location: ${state.location.locationName.capitalize()}",
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}