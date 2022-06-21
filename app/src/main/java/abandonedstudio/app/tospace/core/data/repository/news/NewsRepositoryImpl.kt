package abandonedstudio.app.tospace.core.data.repository.news

import abandonedstudio.app.tospace.core.data.remote.news.events.EventsRemoteApi
import abandonedstudio.app.tospace.core.domain.model.news.SpaceEvent
import abandonedstudio.app.tospace.core.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val eventsApi: EventsRemoteApi
) : NewsRepository {

    override suspend fun loadUpcomingEvents(): List<SpaceEvent> =
        eventsApi.loadUpcomingEvents().toSpaceEvents()
}