package abandonedstudio.app.tospace.core.data.repository.spacex

import abandonedstudio.app.tospace.core.data.remote.spacex.dto.response.SpaceXSingleLaunchResponse
import abandonedstudio.app.tospace.core.domain.model.Launch

fun SpaceXSingleLaunchResponse.toLaunch() =
    this.docs.first().let {
        Launch(
            missionName = it.name,
            logoImgPath = it.links?.patch?.small,
            rocket = it.rocket?.name,
            flightNumber = it.flightNumber,
            timeStamp = it.dateUnix,
            links = Launch.Links(
                wikipedia = it.links?.wikipedia,
                yt = it.links?.webcast,
                reddit = it.links?.reddit?.campaign
            ),
            details = it.details,
            launchPad = it.launchpad?.name,
            payloads = it.payloads?.map { payload ->
                Launch.Payload(
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