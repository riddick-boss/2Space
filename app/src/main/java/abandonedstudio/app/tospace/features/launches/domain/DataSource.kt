package abandonedstudio.app.tospace.features.launches.domain

import abandonedstudio.app.tospace.core.domain.model.launches.Launch
import abandonedstudio.app.tospace.core.domain.model.launches.LaunchesPagingSource
import abandonedstudio.app.tospace.core.domain.repository.LaunchesRepository
import abandonedstudio.app.tospace.features.launches.data.UpcomingLaunch
import javax.inject.Inject

class DataSource @Inject constructor(
    private val launchesRepository: LaunchesRepository
) {

    suspend fun loadUpcomingLaunches(next: String?): LaunchesPagingSource.Page<UpcomingLaunch> {
        val response = launchesRepository.loadUpcomingLaunches(next)
        return LaunchesPagingSource.Page(
            data = response.data.map { it.toUpcomingLaunch() },
            next = response.next
        )
    }
}

class UpcomingLaunchesPagingSource(
    private val dataSource: DataSource
) : LaunchesPagingSource<UpcomingLaunch>() {
    override suspend fun loadPage(next: String?): Page<UpcomingLaunch> = dataSource.loadUpcomingLaunches(next)
}

private fun Launch.toUpcomingLaunch() =
    UpcomingLaunch(
        id = id,
        name = name,
        status = status?.let { status ->
            UpcomingLaunch.LaunchStatus(
                id = status.id,
                name = status.name,
                shortName = status.shortName,
                description = status.description
            )
        },
        launchPad = launchPad?.let { pad ->
            UpcomingLaunch.LaunchPad(
                name = pad.name,
                location = pad.location
            )
        },
        description = description,
        imageUrl = imageUrl,
        infographicUrl = infographicUrl,
        probability = probability,
        timeStamp = timeStamp
    )