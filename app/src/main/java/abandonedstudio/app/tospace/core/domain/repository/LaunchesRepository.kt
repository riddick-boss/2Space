package abandonedstudio.app.tospace.core.domain.repository

import abandonedstudio.app.tospace.core.domain.model.launches.DetailedLaunch
import abandonedstudio.app.tospace.core.domain.model.launches.Launch
import abandonedstudio.app.tospace.core.domain.model.launches.LaunchesPagingSource

interface LaunchesRepository {

    suspend fun fetchNextLaunch(): DetailedLaunch

    suspend fun fetchPreviousLaunch(): DetailedLaunch

    suspend fun loadUpcomingLaunches(next: String?): LaunchesPagingSource.Page<Launch>

    suspend fun loadUpcomingLaunch(): Launch
}