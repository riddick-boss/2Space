package abandonedstudio.app.tospace.features.launches.presentation

import abandonedstudio.app.tospace.core.domain.model.PastSpaceXLaunch
import abandonedstudio.app.tospace.core.domain.model.UpcomingSpaceXLaunch
import abandonedstudio.app.tospace.features.launches.domain.DataSource
import abandonedstudio.app.tospace.features.launches.domain.PastLaunchesPagingSource
import abandonedstudio.app.tospace.features.launches.domain.UpcomingLaunchesPagingSource
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
class LaunchesViewModel @Inject constructor(
    application: Application,
    private val dataSource: DataSource
): AndroidViewModel(application) {

    private val upcomingPagingConfig = PagingConfig(
        initialLoadSize = 10,
        pageSize = 10,
        prefetchDistance = 2,
        enablePlaceholders = true
    )

    val upcomingLaunchesFlow: Flow<PagingData<UpcomingSpaceXLaunch>> by lazy {
        Pager(upcomingPagingConfig) {
            UpcomingLaunchesPagingSource(dataSource)
        }.flow.cachedIn(viewModelScope)
    }

    private val pastPagingConfig = PagingConfig(
        initialLoadSize = 5,
        pageSize = 5,
        prefetchDistance = 2,
        enablePlaceholders = true
    )

    val pastLaunchesFlow: Flow<PagingData<PastSpaceXLaunch>> by lazy {
        Pager(pastPagingConfig) {
            PastLaunchesPagingSource(dataSource)
        }.flow.cachedIn(viewModelScope)
    }
}