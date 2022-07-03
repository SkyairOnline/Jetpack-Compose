package com.arudo.jetpackcompose.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arudo.jetpackcompose.domain.model.Games
import com.arudo.jetpackcompose.domain.repository.GamesRepository
import com.arudo.jetpackcompose.network.repository.GamesRepositoryImpl
import com.arudo.jetpackcompose.utils.Response
import kotlinx.coroutines.launch

class DetailViewModel constructor(
    private val gamesRepository: GamesRepository = GamesRepositoryImpl(),
): ViewModel() {
    private val _gamesState = mutableStateOf<Response<Games>>(Response.Success(null))
    val gamesState: State<Response<Games>> = _gamesState

    fun getDetailGames(id: Int) {
        viewModelScope.launch {
            gamesRepository.getDetailGames(id).collect { response ->
                _gamesState.value = response
            }
        }
    }
}