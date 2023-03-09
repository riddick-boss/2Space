package abandonedstudio.app.tospace.domain.infrastructure.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

abstract class DefaultPagingSource<KEY: Any, DATA: Any> : PagingSource<KEY, DATA>() {

    override fun getRefreshKey(state: PagingState<KEY, DATA>): KEY? = null
}