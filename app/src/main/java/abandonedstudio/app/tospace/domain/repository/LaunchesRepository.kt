package abandonedstudio.app.tospace.domain.repository

import abandonedstudio.app.tospace.domain.model.launches.DetailedLaunch
import abandonedstudio.app.tospace.domain.model.launches.Launch
import abandonedstudio.app.tospace.domain.infrastructure.paging.LaunchesPagingSource

interface LaunchesRepository {

    suspend fun fetchNextLaunch(): DetailedLaunch

    suspend fun fetchPreviousLaunch(): DetailedLaunch

    suspend fun loadUpcomingLaunches(next: String?): LaunchesPagingSource.Page<Launch>

    suspend fun loadUpcomingLaunch(): Launch
}