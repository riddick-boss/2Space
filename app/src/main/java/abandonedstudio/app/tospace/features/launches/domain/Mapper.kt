package abandonedstudio.app.tospace.features.launches.domain

import abandonedstudio.app.tospace.domain.model.launches.Launch
import abandonedstudio.app.tospace.domain.model.util.DateFormat
import abandonedstudio.app.tospace.features.launches.data.UpcomingLaunch
import javax.inject.Inject

class Mapper @Inject constructor() {
    companion object {
        private const val PRECISION_THRESHOLD = 3 //lower than 3 means precision better than day
    }

    fun toUpcomingLaunch(launch: Launch): UpcomingLaunch {
        val startTime = calculateStartTime(launch.timeStamp, launch.netPrecision)
        return UpcomingLaunch(
            id = launch.id,
            name = launch.name,
            status = launch.status?.let { status ->
                UpcomingLaunch.LaunchStatus(
                    id = status.id,
                    name = status.name,
                    shortName = status.shortName,
                    description = status.description
                )
            },
            launchPad = launch.launchPad?.let { pad ->
                UpcomingLaunch.LaunchPad(
                    name = pad.name,
                    location = pad.location
                )
            },
            description = launch.description,
            imageUrl = launch.imageUrl,
            infographicUrl = launch.infographicUrl,
            probability = launch.probability,
            startTime = startTime
        )
    }

    private fun calculateStartTime(timeStamp: DateFormat?, netPrecision: Int?): String? {
        return when {
            timeStamp == null -> null
            netPrecision != null && netPrecision < PRECISION_THRESHOLD -> timeStamp.format(DateFormat.Precision.YEAR)
            else -> "NET: ${timeStamp.monthAndYear}"
        }
    }
}