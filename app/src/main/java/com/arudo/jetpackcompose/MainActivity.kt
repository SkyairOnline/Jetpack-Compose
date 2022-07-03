package com.arudo.jetpackcompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arudo.jetpackcompose.fragment.detail.DetailFragment
import com.arudo.jetpackcompose.fragment.home.HomeFragment
import com.arudo.jetpackcompose.ui.theme.JetpackComposeTheme
import com.arudo.jetpackcompose.utils.Const.DETAIL_ARG_GAMES_ID
import com.arudo.jetpackcompose.utils.Route
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                JetpackComposeAppScreen()
            }
        }
    }
}

@Composable
fun JetpackComposeAppScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.Home.route,
    ) {
        composable(route = Route.Home.route) {
            HomeFragment(
                onClickToDetailScreen = { gamesId ->
                    navController.navigate(
                        Route.Detail.createRoute(gamesId)
                    )
                }
            )
        }
        composable(
            route = Route.Detail.route,
            arguments = listOf(
                navArgument(DETAIL_ARG_GAMES_ID){
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val gamesId = backStackEntry.arguments?.getInt(DETAIL_ARG_GAMES_ID)
            requireNotNull(gamesId) { "gamesId parameter wasn't found. Please make sure it's set!" }
            DetailFragment(id = gamesId)
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun JetpackComposeAppScreenPreview() {
    JetpackComposeTheme {
        JetpackComposeAppScreen()
    }
}