package com.arudo.jetpackcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.arudo.jetpackcompose.domain.model.Games
import com.arudo.jetpackcompose.domain.repository.GamesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    gamesRepository: GamesRepository,
): ViewModel() {

    val gamesListState: Flow<PagingData<Games>> =
        gamesRepository.getAllGames().cachedIn(viewModelScope)
}