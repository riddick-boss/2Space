package abandonedstudio.app.tospace.core.data.repository.launches

import abandonedstudio.app.tospace.core.data.remote.launches.LaunchesRemoteApi
import abandonedstudio.app.tospace.domain.model.launches.DetailedLaunch
import abandonedstudio.app.tospace.domain.model.launches.Launch
import abandonedstudio.app.tospace.domain.infrastructure.paging.LaunchesPagingSource
import abandonedstudio.app.tospace.domain.repository.LaunchesRepository
import javax.inject.Inject

class LaunchesRepositoryImpl @Inject constructor(
    private val launchesRemoteApi: LaunchesRemoteApi
) : LaunchesRepository {

    override suspend fun fetchNextLaunch(): DetailedLaunch =
        launchesRemoteApi.fetchNextLaunch().toDetailedLaunch()

    override suspend fun fetchPreviousLaunch(): DetailedLaunch =
        launchesRemoteApi.fetchPreviousLaunch().toDetailedLaunch()

    override suspend fun loadUpcomingLaunches(next: String?): LaunchesPagingSource.Page<Launch> =
        launchesRemoteApi.loadUpcomingLaunches(next).toLaunchesPage()

    override suspend fun loadUpcomingLaunch(): Launch =
        launchesRemoteApi.loadUpcomingLaunches(null).toLaunchesPage().data.first()
}