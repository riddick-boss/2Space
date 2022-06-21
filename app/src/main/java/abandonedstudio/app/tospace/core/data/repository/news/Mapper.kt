package abandonedstudio.app.tospace.core.data.repository.news

import abandonedstudio.app.tospace.core.data.remote.news.events.dto.TheSpaceDevsEventResponse
import abandonedstudio.app.tospace.core.domain.model.news.SpaceEvent

fun TheSpaceDevsEventResponse.toSpaceEvents(): List<SpaceEvent> =
    this.results?.map {
        SpaceEvent(
            name = it?.name,
            description = it?.description,
            imageUrl = it?.featureImage,
            newsUrl = it?.newsUrl,
            videoUrl = it?.videoUrl,
            type = it?.type?.name
        )
    } ?: emptyList()