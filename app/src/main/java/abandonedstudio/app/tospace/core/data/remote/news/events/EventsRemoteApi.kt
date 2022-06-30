package abandonedstudio.app.tospace.core.data.remote.news.events

import abandonedstudio.app.tospace.core.data.remote.news.events.dto.TheSpaceDevsEventResponse

interface EventsRemoteApi {

    suspend fun loadUpcomingEvents(): TheSpaceDevsEventResponse
}