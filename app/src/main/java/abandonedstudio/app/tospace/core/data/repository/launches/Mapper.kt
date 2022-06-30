package abandonedstudio.app.tospace.core.data.repository.launches

import abandonedstudio.app.tospace.core.data.remote.launches.dto.AllLaunchesResponse
import abandonedstudio.app.tospace.core.domain.model.launches.Launch
import abandonedstudio.app.tospace.core.domain.model.launches.LaunchesPagingSource
import java.text.SimpleDateFormat

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
                timeStampMillis = it?.windowStart?.let { start ->
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(start)?.time
                }
            )
        } ?: emptyList(),
        next = next
    )