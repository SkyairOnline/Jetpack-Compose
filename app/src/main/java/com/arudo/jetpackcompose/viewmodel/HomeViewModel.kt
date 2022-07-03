package com.arudo.jetpackcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.arudo.jetpackcompose.domain.model.Games
import com.arudo.jetpackcompose.domain.repository.GamesRepository
import com.arudo.jetpackcompose.network.repository.GamesRepositoryImpl
import kotlinx.coroutines.flow.Flow

class HomeViewModel constructor(
    gamesRepository: GamesRepository = GamesRepositoryImpl(),
): ViewModel() {

    val gamesListState: Flow<PagingData<Games>> =
        gamesRepository.getAllGames().cachedIn(viewModelScope)
}