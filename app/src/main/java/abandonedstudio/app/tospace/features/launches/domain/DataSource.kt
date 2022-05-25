package abandonedstudio.app.tospace.features.launches.domain

import abandonedstudio.app.tospace.core.domain.model.PastSpaceXLaunch
import abandonedstudio.app.tospace.core.domain.model.UpcomingSpaceXLaunch
import abandonedstudio.app.tospace.core.domain.repository.SpaceXRepository
import abandonedstudio.app.tospace.core.domain.util.DefaultPagingSource
import javax.inject.Inject

class DataSource @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) {
    suspend fun loadUpcomingLaunches(page: Int, limit: Int): DefaultPagingSource.Page<UpcomingSpaceXLaunch> =
        spaceXRepository.loadUpcomingLaunches(page, limit)

    suspend fun loadPastLaunches(page: Int, limit: Int): DefaultPagingSource.Page<PastSpaceXLaunch> =
        spaceXRepository.loadPastLaunches(page, limit)
}

class UpcomingLaunchesPagingSource(
    private val dataSource: DataSource
) : DefaultPagingSource<UpcomingSpaceXLaunch>() {
    override suspend fun loadPage(page: Int, limit: Int): Page<UpcomingSpaceXLaunch> = dataSource.loadUpcomingLaunches(page, limit)
}

class PastLaunchesPagingSource(
    private val dataSource: DataSource
): DefaultPagingSource<PastSpaceXLaunch>() {
    override suspend fun loadPage(page: Int, limit: Int): Page<PastSpaceXLaunch> = dataSource.loadPastLaunches(page, limit)
}