package abandonedstudio.app.tospace.core.data.remote.news.events.ktor

import abandonedstudio.app.tospace.core.data.remote.news.events.EventsRemoteApi
import abandonedstudio.app.tospace.core.data.remote.news.events.dto.TheSpaceDevsEventResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import javax.inject.Inject

class KtorEventsRemoteApi @Inject constructor(
    private val client: HttpClient
) : EventsRemoteApi {

    override suspend fun loadUpcomingEvents(): TheSpaceDevsEventResponse =
        client.get(Routes.EVENTS_URL).body()
}