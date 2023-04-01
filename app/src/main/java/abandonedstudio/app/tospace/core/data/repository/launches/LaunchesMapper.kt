package abandonedstudio.app.tospace.core.data.repository.launches

import abandonedstudio.app.tospace.core.data.remote.launches.dto.AllLaunchesResponse
import abandonedstudio.app.tospace.core.data.remote.launches.dto.LaunchDetailedResponse
import abandonedstudio.app.tospace.domain.model.launches.DetailedLaunch
import abandonedstudio.app.tospace.domain.model.launches.Launch
import abandonedstudio.app.tospace.domain.infrastructure.paging.LaunchesPagingSource
import android.os.Build
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.*
import javax.inject.Inject

class LaunchesMapper @Inject constructor() {
    fun toDetailedLaunch(response: LaunchDetailedResponse): DetailedLaunch = response.let {
        DetailedLaunch(
            missionName = it.name,
            logoImgPath = it.mission_patches?.firstOrNull()?.image_url
                ?: it.program?.firstOrNull()?.missionPatches?.firstOrNull()?.imageUrl ?: it.infographic
                ?: it.program?.firstOrNull { program ->  program?.url != null }?.url,
            rocket = it.rocket?.configuration?.fullName,
            flightNumber = it.agencyLaunchAttemptCount,
            timeStamp = calculateTimeStamp(it.windowStart, it.windowEnd),
            links = DetailedLaunch.Links(
                wikipedia = it.program?.firstOrNull()?.wikiUrl,
                yt = it.vidURLs?.firstOrNull { urls -> urls?.url?.contains("youtube") ?: false }?.url
                    ?: it.vidURLs?.firstOrNull()?.url,
                reddit = null
            ),
            details = it.mission?.description,
            launchPad = it.pad?.name,
            payloads = emptyList()
        )
    }

    fun toLaunchesPage(response: AllLaunchesResponse): LaunchesPagingSource.Page<Launch> = response.let {
        LaunchesPagingSource.Page(
            data = it.results?.map { result ->
                Launch(
                    id = result?.id,
                    name = result?.name,
                    status = result?.status.let { status ->
                        Launch.LaunchStatus(
                            id = status?.id,
                            name = status?.name,
                            shortName = status?.abbrev,
                            description = status?.description
                        )
                    },
                    launchPad = result?.pad.let { pad ->
                        Launch.LaunchPad(
                            name = pad?.name,
                            location = pad?.location?.name
                        )
                    },
                    description = result?.mission?.description,
                    imageUrl = result?.image,
                    infographicUrl = result?.infographic,
                    probability = result?.probability,
                    timeStampMillis = calculateTimeStamp(result?.windowStart, result?.windowEnd)
                )
            } ?: emptyList(),
            next = it.next
        )
    }

    private fun calculateTimeStamp(windowStart: String?, windowEnd: String?): Long? {
        if (windowStart == null && windowEnd == null) return null

        val now = System.currentTimeMillis()

        val zone = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            TimeZone.getTimeZone(ZoneId.of("UTC"))
        } else {
            TimeZone.getDefault()
        }

        val start = windowStart?.let { SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").apply { timeZone = zone }.parse(it)?.time } // convert window start to millis

        if (start != null && start > now) { // if window start has not started yet
            return start
        }

        val end = windowEnd?.let { SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").apply { timeZone = zone }.parse(it)?.time } // convert window end to millis

        if (end != null && end > now) { // if window end has not started yet
            return end
        }

        return start
    }
}