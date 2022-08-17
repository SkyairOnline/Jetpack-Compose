package com.arudo.jetpackcompose.network.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arudo.jetpackcompose.domain.model.Games
import com.arudo.jetpackcompose.domain.model.GamesResponse

class GamesPagingSource(
    private val response: suspend (Int) -> GamesResponse,
) : PagingSource<Int, Games>() {

    override fun getRefreshKey(state: PagingState<Int, Games>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Games> {
        return try {
            val nextPage = params.key ?: 1
            val gamesList = response.invoke(nextPage)
            LoadResult.Page(
                data = gamesList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = gamesList
                    .next
                    ?.substringAfter("page=")
                    ?.substringBefore("&")
                    ?.toInt() ?: nextPage
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}