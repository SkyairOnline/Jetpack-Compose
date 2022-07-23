package com.arudo.jetpackcompose.fragment.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.arudo.jetpackcompose.domain.model.Games
import com.arudo.jetpackcompose.ui.component.ErrorButton
import com.arudo.jetpackcompose.ui.component.LoadingCircular
import com.arudo.jetpackcompose.ui.component.ProductCard
import com.arudo.jetpackcompose.ui.theme.JetpackComposeTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    gamesList: LazyPagingItems<Games>? = null,
    onClickToDetailScreen: (Int) -> Unit = {},
) {
    if(gamesList == null) return
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(minSize = 100.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(gamesList.itemCount) { index ->
            gamesList[index].let { games ->
                val id = games?.id
                val name = games?.name ?: ""
                val imageUrl = games?.backgroundImage ?: ""
                val releaseDate = games?.released ?: ""
                ProductCard(
                    modifier = modifier
                        .padding(8.dp),
                    name = name,
                    imageUrl = imageUrl,
                    releaseDate = releaseDate,
                    onClickCard = {
                        id?.let {
                            onClickToDetailScreen.invoke(id)
                        }
                    }
                )
            }
        }
        gamesList.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item(
                        span = { GridItemSpan(maxLineSpan) }
                    ) {
                        LoadingCircular(
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = gamesList.loadState.refresh as LoadState.Error
                    item(
                        span = { GridItemSpan(maxLineSpan) }
                    ) {
                        ErrorButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = e.error.localizedMessage ?: "",
                            onClick = {
                                retry()
                            }
                        )
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item(
                        span = { GridItemSpan(maxLineSpan) }
                    ) {
                        LoadingCircular(
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = gamesList.loadState.append as LoadState.Error
                    item(
                        span = { GridItemSpan(maxLineSpan) }
                    ) {
                        ErrorButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = e.error.localizedMessage ?: "",
                            onClick = {
                                retry()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    JetpackComposeTheme {
        HomeScreen()
    }
}