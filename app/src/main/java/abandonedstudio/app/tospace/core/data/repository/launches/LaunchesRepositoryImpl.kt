package abandonedstudio.app.tospace.core.data.repository.launches

import abandonedstudio.app.tospace.core.data.remote.launches.LaunchesRemoteApi
import abandonedstudio.app.tospace.core.domain.model.launches.Launch
import abandonedstudio.app.tospace.core.domain.model.launches.LaunchesPagingSource
import abandonedstudio.app.tospace.core.domain.repository.LaunchesRepository
import javax.inject.Inject

class LaunchesRepositoryImpl @Inject constructor(
    private val launchesRemoteApi: LaunchesRemoteApi
) : LaunchesRepository {

    override suspend fun loadUpcomingLaunches(next: String?): LaunchesPagingSource.Page<Launch> =
        launchesRemoteApi.loadUpcomingLaunches(next).toLaunchesPage()
}