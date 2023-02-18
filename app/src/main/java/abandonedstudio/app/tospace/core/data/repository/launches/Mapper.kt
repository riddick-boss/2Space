package abandonedstudio.app.tospace.core.data.repository.launches

import abandonedstudio.app.tospace.core.data.remote.launches.dto.AllLaunchesResponse
import abandonedstudio.app.tospace.core.data.remote.launches.dto.LaunchDetailedResponse
import abandonedstudio.app.tospace.core.domain.model.launches.Launch
import abandonedstudio.app.tospace.core.domain.model.launches.LaunchesPagingSource
import abandonedstudio.app.tospace.core.domain.model.spacex.DetailedLaunch
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.*

fun LaunchDetailedResponse.toDetailedLaunch(): DetailedLaunch =
    DetailedLaunch(
        missionName = name,
        logoImgPath = mission_patches?.firstOrNull()?.image_url ?: program?.firstOrNull()?.missionPatches?.firstOrNull()?.imageUrl ?: infographic ?: program?.firstOrNull { it?.url != null }?.url,
        rocket = rocket?.configuration?.fullName,
        flightNumber = agencyLaunchAttemptCount,
        timeStamp = calculateTimeStamp(windowStart, windowEnd),
        links = DetailedLaunch.Links(
            wikipedia = program?.firstOrNull()?.wikiUrl,
            yt = vidURLs?.firstOrNull { it?.url?.contains("youtube") ?: false }?.url ?:vidURLs?.firstOrNull()?.url,
            reddit = null
        ),
        details = mission?.description,
        launchPad = pad?.name,
        payloads = emptyList()
    )

fun AllLaunchesResponse.toLaunchesPage(): LaunchesPagingSource.Page<Launch> =
    LaunchesPagingSource.Page(
        data = results?.map {
            Launch(
                id = it?.id,
                name = it?.name,
                status = it?.status.let { status ->
                    Launch.LaunchStatus(
                        id = status?.id,
                        name = status?.name,
                        shortName = status?.abbrev,
                        description = status?.description
                    )
                },
                launchPad = it?.pad.let { pad ->
                    Launch.LaunchPad(
                        name = pad?.name,
                        location = pad?.location?.name
                    )
                },
                description = it?.mission?.description,
                imageUrl = it?.image,
                infographicUrl = it?.infographic,
                probability = it?.probability,
                timeStampMillis = calculateTimeStamp(it?.windowStart, it?.windowEnd)
            )
        } ?: emptyList(),
        next = next
    )


private fun calculateTimeStamp(windowStart: String?, windowEnd: String?): Long? {
    if (windowStart == null && windowEnd == null) return null

    val now = System.currentTimeMillis()

    val start = windowStart?.let { SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").apply { timeZone = TimeZone.getTimeZone(ZoneId.of("UTC")) }.parse(it)?.time } // convert window start to millis

    if (start != null && start > now) { // if window start has not started yet
        return start
    }

    val end = windowEnd?.let { SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").apply { timeZone = TimeZone.getTimeZone(ZoneId.of("UTC")) }.parse(it)?.time } // convert window start to millis

    if (end != null && end > now) { // if window end has not started yet
        return end
    }

    return start
}