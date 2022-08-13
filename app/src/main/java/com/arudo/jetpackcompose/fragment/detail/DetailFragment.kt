package com.arudo.jetpackcompose.fragment.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.arudo.jetpackcompose.R
import com.arudo.jetpackcompose.fragment.detail.screen.DetailScreen
import com.arudo.jetpackcompose.ui.component.ErrorButton
import com.arudo.jetpackcompose.ui.component.LoadingCircular
import com.arudo.jetpackcompose.ui.theme.JetpackComposeTheme
import com.arudo.jetpackcompose.utils.Response
import com.arudo.jetpackcompose.viewmodel.DetailViewModel

@Composable
fun DetailFragment(
    modifier: Modifier = Modifier,
    detailViewModel: DetailViewModel = hiltViewModel(),
    id: Int? = null,
) {
    if(id == null) return

    fun launch() {
        detailViewModel.getDetailGames(id)
    }

    launch()
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when(val gamesResponse = detailViewModel.gamesState.value){
            is Response.Loading -> {
                LoadingCircular(
                    modifier = Modifier.fillMaxWidth()
                )
            }
            is Response.Success -> {
                DetailScreen(
                    games = gamesResponse.data
                )
            }
            is Response.Failure -> {
                ErrorButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.error_message),
                    onClick = {
                        launch()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailFragmentPreview() {
    JetpackComposeTheme {
        DetailFragment()
    }
}