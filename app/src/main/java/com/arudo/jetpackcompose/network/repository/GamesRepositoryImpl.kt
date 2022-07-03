package com.arudo.jetpackcompose.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.arudo.jetpackcompose.domain.model.Games
import com.arudo.jetpackcompose.domain.repository.GamesRepository
import com.arudo.jetpackcompose.network.pagingsource.GamesPagingSource
import com.arudo.jetpackcompose.network.service.GamesService
import com.arudo.jetpackcompose.utils.Const.PAGE_SIZE
import com.arudo.jetpackcompose.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GamesRepositoryImpl constructor(
    private val gamesService: GamesService = GamesService.getInstance(),
): GamesRepository {
    override fun getAllGames(): Flow<PagingData<Games>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = true
        ),
        pagingSourceFactory = {
            GamesPagingSource(
                response = { pageNext ->
                    gamesService.getAllGames(
                        page = pageNext,
                        pageSize = PAGE_SIZE,
                    )
                }
            )
        }
    ).flow

    override fun getDetailGames(id: Int): Flow<Response<Games>> = flow{
        try {
            emit(Response.Loading)
            val responseApi = gamesService.getGamesDetail(id = id)
            emit(Response.Success(responseApi))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }.flowOn(Dispatchers.IO)
}