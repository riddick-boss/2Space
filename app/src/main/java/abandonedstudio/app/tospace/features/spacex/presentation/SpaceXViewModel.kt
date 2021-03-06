package abandonedstudio.app.tospace.features.spacex.presentation

import abandonedstudio.app.tospace.core.domain.model.spacex.UpcomingSpaceXLaunch
import abandonedstudio.app.tospace.features.spacex.data.PastLaunch
import abandonedstudio.app.tospace.features.spacex.domain.DataSource
import abandonedstudio.app.tospace.features.spacex.domain.PastLaunchesPagingSource
import abandonedstudio.app.tospace.features.spacex.domain.UpcomingLaunchesPagingSource
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SpaceXViewModel @Inject constructor(
    application: Application,
    private val dataSource: DataSource
) : AndroidViewModel(application) {

    private val pagingConfig = PagingConfig(
        initialLoadSize = 10,
        pageSize = 10,
        prefetchDistance = 2,
        enablePlaceholders = true
    )

    val upcomingLaunchesFlow: Flow<PagingData<UpcomingSpaceXLaunch>> by lazy {
        Pager(pagingConfig) {
            UpcomingLaunchesPagingSource(dataSource)
        }.flow.cachedIn(viewModelScope)
    }

    val pastLaunchesFlow: Flow<PagingData<PastLaunch>> by lazy {
        Pager(pagingConfig) {
            PastLaunchesPagingSource(dataSource)
        }.flow.cachedIn(viewModelScope)
    }
}