package abandonedstudio.app.tospace.core.data.repository.spacex

import abandonedstudio.app.tospace.core.data.remote.spacex.dto.response.PastSpaceXLaunchesResponse
import abandonedstudio.app.tospace.core.data.remote.spacex.dto.response.SpaceXDetailedLaunchResponse
import abandonedstudio.app.tospace.core.data.remote.spacex.dto.response.UpcomingSpaceXLaunchesResponse
import abandonedstudio.app.tospace.core.domain.model.DetailedLaunch
import abandonedstudio.app.tospace.core.domain.model.PastSpaceXLaunch
import abandonedstudio.app.tospace.core.domain.model.UpcomingSpaceXLaunch
import abandonedstudio.app.tospace.core.domain.util.DefaultPagingSource

fun SpaceXDetailedLaunchResponse.toDetailedLaunch(): DetailedLaunch =
    this.docs.first().let {
        DetailedLaunch(
            missionName = it.name,
            logoImgPath = it.links?.patch?.small,
            rocket = it.rocket?.name,
            flightNumber = it.flightNumber,
            timeStamp = it.dateUnix,
            links = DetailedLaunch.Links(
                wikipedia = it.links?.wikipedia,
                yt = it.links?.webcast,
                reddit = it.links?.reddit?.campaign
            ),
            details = it.details,
            launchPad = it.launchpad?.name,
            payloads = it.payloads?.map { payload ->
                DetailedLaunch.Payload(
                    type = payload?.type,
                    massKg = payload?.massKg,
                    orbit = payload?.orbit,
                    inclination = payload?.inclinationDeg,
                    periodMin = payload?.periodMin,
                    customers = payload?.customers ?: emptyList()
                )
            } ?: emptyList()
        )
    }

fun UpcomingSpaceXLaunchesResponse.toLaunchPaginationData(): DefaultPagingSource.Page<UpcomingSpaceXLaunch> =
    DefaultPagingSource.Page(
        page = this.page,
        hasNext = this.hasNextPage,
        data = this.docs.map {
            UpcomingSpaceXLaunch(
                missionName = it.name,
                logoImgPath = it.links?.patch?.small,
                rocket = it.rocket?.name,
                timeStamp = it.dateUnix
            )
        }
    )

fun PastSpaceXLaunchesResponse.toLaunchPaginationData(): DefaultPagingSource.Page<PastSpaceXLaunch> =
    DefaultPagingSource.Page(
        page = this.page,
        hasNext = this.hasNextPage,
        data = this.docs.map {
            PastSpaceXLaunch(
                missionName = it.name
            )
        }
    )