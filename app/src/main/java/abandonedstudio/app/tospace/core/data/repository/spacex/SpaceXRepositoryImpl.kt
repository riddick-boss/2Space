package abandonedstudio.app.tospace.core.data.repository.spacex

import abandonedstudio.app.tospace.core.data.remote.spacex.SpaceXRemoteApi
import abandonedstudio.app.tospace.core.domain.model.launches.DetailedLaunch
import abandonedstudio.app.tospace.core.domain.model.launches.PastSpaceXLaunch
import abandonedstudio.app.tospace.core.domain.model.launches.UpcomingSpaceXLaunch
import abandonedstudio.app.tospace.core.domain.repository.SpaceXRepository
import abandonedstudio.app.tospace.core.domain.util.DefaultPagingSource
import javax.inject.Inject

class SpaceXRepositoryImpl @Inject constructor(
    private val remoteApi: SpaceXRemoteApi
) : SpaceXRepository {

    override suspend fun getNextLaunch(): DetailedLaunch =
        remoteApi.getNextLaunch().toDetailedLaunch()

    override suspend fun getLastLaunch(): DetailedLaunch =
        remoteApi.getLastLaunch().toDetailedLaunch()

    override suspend fun loadUpcomingLaunches(page: Int, limit: Int): DefaultPagingSource.Page<UpcomingSpaceXLaunch> =
        remoteApi.loadUpcomingLaunches(page, limit).toLaunchPaginationData()

    override suspend fun loadPastLaunches(page: Int, limit: Int): DefaultPagingSource.Page<PastSpaceXLaunch> =
        remoteApi.loadPastLaunches(page, limit).toLaunchPaginationData()
}