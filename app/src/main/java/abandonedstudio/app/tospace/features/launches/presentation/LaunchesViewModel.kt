package abandonedstudio.app.tospace.features.launches.presentation

import abandonedstudio.app.tospace.features.launches.data.UpcomingLaunch
import abandonedstudio.app.tospace.features.launches.domain.DataSource
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
    dataSource: DataSource
) : AndroidViewModel(application) {

    private val pagingConfig = PagingConfig(
        initialLoadSize = 20,
        pageSize = 20,
        prefetchDistance = 2,
        enablePlaceholders = true
    )

    val upcomingLaunchesFlow: Flow<PagingData<UpcomingLaunch>> by lazy {
        Pager(pagingConfig) {
            UpcomingLaunchesPagingSource(dataSource)
        }.flow.cachedIn(viewModelScope)
    }
}