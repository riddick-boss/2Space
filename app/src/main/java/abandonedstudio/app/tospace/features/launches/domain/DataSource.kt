package abandonedstudio.app.tospace.features.launches.domain

import abandonedstudio.app.tospace.domain.infrastructure.paging.LaunchesPagingSource
import abandonedstudio.app.tospace.domain.repository.LaunchesRepository
import abandonedstudio.app.tospace.features.launches.data.UpcomingLaunch
import javax.inject.Inject

class DataSource @Inject constructor(
    private val launchesRepository: LaunchesRepository,
    private val mapper: Mapper
) {

    suspend fun loadUpcomingLaunches(next: String?): LaunchesPagingSource.Page<UpcomingLaunch> {
        val response = launchesRepository.loadUpcomingLaunches(next)
        return LaunchesPagingSource.Page(
            data = response.data.map { mapper.toUpcomingLaunch(it) },
            next = response.next
        )
    }
}

class UpcomingLaunchesPagingSource(
    private val dataSource: DataSource
) : LaunchesPagingSource<UpcomingLaunch>() {
    override suspend fun loadPage(next: String?): Page<UpcomingLaunch> = dataSource.loadUpcomingLaunches(next)
}