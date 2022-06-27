package com.arudo.jetpackcompose.fragment.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arudo.jetpackcompose.ui.component.ProductCard
import com.arudo.jetpackcompose.ui.theme.JetpackComposeTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onClickToDetailScreen: () -> Unit = {},
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(minSize = 100.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(60) {
            ProductCard(
                modifier = modifier
                    .padding(
                        top = 16.dp
                    ),
                onClickToDetailScreen = onClickToDetailScreen
            )
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