package abandonedstudio.app.tospace.features.launches.domain

import abandonedstudio.app.tospace.core.domain.model.Launch
import abandonedstudio.app.tospace.core.domain.repository.SpaceXRepository
import abandonedstudio.app.tospace.core.domain.util.DefaultPagingSource
import javax.inject.Inject

class DataSource @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) {
    suspend fun loadUpcomingLaunches(page: Int, limit: Int): DefaultPagingSource.Page<Launch> =
        spaceXRepository.loadUpcomingLaunches(page, limit)
}

class UpcomingLaunchesPagingSource @Inject constructor(
    private val dataSource: DataSource
) : DefaultPagingSource<Launch>() {

    override suspend fun loadPage(page: Int, limit: Int): Page<Launch> = dataSource.loadUpcomingLaunches(page, limit)
}