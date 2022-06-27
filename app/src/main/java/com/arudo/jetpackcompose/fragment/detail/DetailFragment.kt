package com.arudo.jetpackcompose.fragment.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arudo.jetpackcompose.fragment.detail.screen.DetailScreen
import com.arudo.jetpackcompose.ui.theme.JetpackComposeTheme

@Composable
fun DetailFragment(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        DetailScreen()
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