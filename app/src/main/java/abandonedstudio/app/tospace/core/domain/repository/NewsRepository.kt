package abandonedstudio.app.tospace.core.domain.repository

import abandonedstudio.app.tospace.core.domain.model.news.SpaceEvent

interface NewsRepository {

    suspend fun loadUpcomingEvents(): List<SpaceEvent>
}