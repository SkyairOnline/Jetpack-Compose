package com.arudo.jetpackcompose.fragment.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.arudo.jetpackcompose.fragment.home.screen.HomeScreen
import com.arudo.jetpackcompose.ui.theme.JetpackComposeTheme
import com.arudo.jetpackcompose.viewmodel.HomeViewModel

@Composable
fun HomeFragment(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel(),
    onClickToDetailScreen: (Int) -> Unit = {},
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        HomeScreen(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp
                ),
            gamesList = homeViewModel.gamesListState.collectAsLazyPagingItems(),
            onClickToDetailScreen = onClickToDetailScreen,
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeFragmentPreview() {
    JetpackComposeTheme {
        HomeFragment()
    }
}