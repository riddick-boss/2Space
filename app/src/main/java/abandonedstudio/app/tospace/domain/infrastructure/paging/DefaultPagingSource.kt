package abandonedstudio.app.tospace.domain.infrastructure.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

abstract class DefaultPagingSource<DATA: Any> : PagingSource<Int, DATA>() {

    final override fun getRefreshKey(state: PagingState<Int, DATA>): Int? = null

    final override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DATA> =
        try {
            val page = loadPage(params.key ?: 1, params.loadSize)

            LoadResult.Page(
                data = page.data,
                prevKey = null,
                nextKey = if (page.hasNext == true) page.page?.plus(1) else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    abstract suspend fun loadPage(page: Int, limit: Int): Page<DATA>

    data class Page<T>(
        val data: List<T>,
        val page: Int?,
        val hasNext: Boolean?
    )
}