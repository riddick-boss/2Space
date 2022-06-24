package abandonedstudio.app.tospace.features.launches.domain

import abandonedstudio.app.tospace.core.domain.model.launches.PastSpaceXLaunch
import abandonedstudio.app.tospace.core.domain.model.launches.UpcomingSpaceXLaunch
import abandonedstudio.app.tospace.core.domain.repository.SpaceXRepository
import abandonedstudio.app.tospace.core.domain.util.DefaultPagingSource
import abandonedstudio.app.tospace.features.launches.data.PastLaunch
import javax.inject.Inject

class DataSource @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) {
    suspend fun loadUpcomingLaunches(
        page: Int,
        limit: Int
    ): DefaultPagingSource.Page<UpcomingSpaceXLaunch> =
        spaceXRepository.loadUpcomingLaunches(page, limit)

    suspend fun loadPastLaunches(
        page: Int,
        limit: Int
    ): DefaultPagingSource.Page<PastLaunch> {
        val response = spaceXRepository.loadPastLaunches(page, limit)
        return DefaultPagingSource.Page(
            data = response.data.map { it.toPastLaunch() },
            page = response.page,
            hasNext = response.hasNext
        )
    }
}

class UpcomingLaunchesPagingSource(
    private val dataSource: DataSource
) : DefaultPagingSource<UpcomingSpaceXLaunch>() {
    override suspend fun loadPage(page: Int, limit: Int): Page<UpcomingSpaceXLaunch> =
        dataSource.loadUpcomingLaunches(page, limit)
}

class PastLaunchesPagingSource(
    private val dataSource: DataSource
) : DefaultPagingSource<PastLaunch>() {
    override suspend fun loadPage(page: Int, limit: Int): Page<PastLaunch> =
        dataSource.loadPastLaunches(page, limit)
}

private fun PastSpaceXLaunch.toPastLaunch() =
    PastLaunch(
        missionName = this.missionName,
        logoImgPath = this.logoImgPath,
        rocket = this.rocket,
        date = this.date,
        links = PastLaunch.Links(
            wikipedia = this.links.wikipedia,
            yt = this.links.yt,
            reddit = this.links.reddit
        ),
        details = this.details,
        launchPad = this.launchPad,
        missionSuccess = this.missionSuccess,
        landingSuccess = this.landingSuccess,
        fairingsRecovered = this.fairingsRecovered,
        core = PastLaunch.Core(
            reused = this.core.reused,
            flightNum = this.core.flightNum
        ),
        landPad = PastLaunch.LandPad(
            name = this.landPad.name,
            fullName = this.landPad.fullName,
            region = this.landPad.region
        ),
        payloads = this.payloads.map { payload ->
            PastLaunch.Payload(
                type = payload.type,
                massKg = payload.massKg,
                orbit = payload.orbit,
                inclination = payload.inclination,
                periodMin = payload.periodMin,
                apoapsisKm = payload.apoapsisKm,
                periapsisKm = payload.periapsisKm,
                customers = payload.customers
            )
        }
    )