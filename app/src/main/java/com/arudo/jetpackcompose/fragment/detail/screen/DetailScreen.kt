package com.arudo.jetpackcompose.fragment.detail.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arudo.jetpackcompose.R
import com.arudo.jetpackcompose.ui.component.ProductHeader
import com.arudo.jetpackcompose.ui.component.ProductImageCarousel
import com.arudo.jetpackcompose.ui.theme.JetpackComposeTheme

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        ProductHeader(
            modifier = Modifier.padding(16.dp)
        )
        ProductImageCarousel(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        )
        Text(
            text = stringResource(id = R.string.product_description_placeholder),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    JetpackComposeTheme {
        DetailScreen()
    }
}