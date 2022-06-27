package com.arudo.jetpackcompose.utils

import com.arudo.jetpackcompose.utils.Const.DETAIL_SCREEN
import com.arudo.jetpackcompose.utils.Const.HOME_SCREEN

sealed class Route(val route: String) {
    object Home: Route(HOME_SCREEN)
    object Detail: Route(DETAIL_SCREEN)
}