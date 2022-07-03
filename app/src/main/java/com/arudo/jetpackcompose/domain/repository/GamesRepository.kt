package com.arudo.jetpackcompose.domain.repository

import androidx.paging.PagingData
import com.arudo.jetpackcompose.domain.model.Games
import com.arudo.jetpackcompose.utils.Response
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    fun getAllGames(): Flow<PagingData<Games>>
    fun getDetailGames(id: Int): Flow<Response<Games>>
}