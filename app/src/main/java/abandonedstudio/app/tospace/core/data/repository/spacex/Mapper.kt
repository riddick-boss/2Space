package abandonedstudio.app.tospace.core.data.repository.spacex

import abandonedstudio.app.tospace.core.data.remote.spacex.dto.response.SpaceXDetailedLaunchResponse
import abandonedstudio.app.tospace.core.data.remote.spacex.dto.response.SpaceXLaunchResponse
import abandonedstudio.app.tospace.core.domain.model.DetailedLaunch
import abandonedstudio.app.tospace.core.domain.model.Launch
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

fun SpaceXLaunchResponse.toLaunchPaginationData(): DefaultPagingSource.Page<Launch> =
    DefaultPagingSource.Page(
        page = this.page,
        hasNext = this.hasNextPage,
        data = this.docs?.map {
            Launch(
                missionName = it?.name
            )
        } ?: emptyList()
    )