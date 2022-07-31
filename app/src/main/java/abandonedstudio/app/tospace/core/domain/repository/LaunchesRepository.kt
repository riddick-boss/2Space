package abandonedstudio.app.tospace.core.domain.repository

import abandonedstudio.app.tospace.core.domain.model.launches.Launch
import abandonedstudio.app.tospace.core.domain.model.launches.LaunchesPagingSource

interface LaunchesRepository {

    suspend fun loadUpcomingLaunches(next: String?): LaunchesPagingSource.Page<Launch>

    suspend fun loadUpcomingLaunch(): Launch
}