package abandonedstudio.app.tospace.core.data.repository.launches

import abandonedstudio.app.tospace.core.data.remote.launches.LaunchesRemoteApi
import abandonedstudio.app.tospace.domain.model.launches.DetailedLaunch
import abandonedstudio.app.tospace.domain.model.launches.Launch
import abandonedstudio.app.tospace.domain.infrastructure.paging.LaunchesPagingSource
import abandonedstudio.app.tospace.domain.repository.LaunchesRepository
import javax.inject.Inject

class LaunchesRepositoryImpl @Inject constructor(
    private val launchesRemoteApi: LaunchesRemoteApi,
    private val mapper: LaunchesMapper
) : LaunchesRepository {

    override suspend fun fetchNextLaunch(): DetailedLaunch {
        val nextLaunch = launchesRemoteApi.fetchNextLaunch()
        return mapper.toDetailedLaunch(nextLaunch)
    }

    override suspend fun fetchPreviousLaunch(): DetailedLaunch {
        val prevLaunch = launchesRemoteApi.fetchPreviousLaunch()
        return mapper.toDetailedLaunch(prevLaunch)
    }

    override suspend fun loadUpcomingLaunches(next: String?): LaunchesPagingSource.Page<Launch> {
        val upcomingLaunches = launchesRemoteApi.loadUpcomingLaunches(next)
        return mapper.toLaunchesPage(upcomingLaunches)
    }

    override suspend fun loadUpcomingLaunch(): Launch {
        val upcomingLaunches = launchesRemoteApi.loadUpcomingLaunches(null)
        return mapper.toLaunchesPage(upcomingLaunches).data.first()
    }
}