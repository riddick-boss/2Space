package abandonedstudio.app.tospace.domain.infrastructure.paging

abstract class LaunchesPagingSource<DATA: Any> : DefaultPagingSource<String, DATA>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, DATA> =
        try {
            val page = loadPage(params.key)

            LoadResult.Page(
                data = page.data,
                prevKey = null,
                nextKey = page.next
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    abstract suspend fun loadPage(next: String?): Page<DATA>

    data class Page<T>(
        val data: List<T>,
        val next: String?
    )
}