package abandonedstudio.app.tospace.domain.repository

import abandonedstudio.app.tospace.domain.model.news.SpaceArticle
import abandonedstudio.app.tospace.domain.model.news.SpaceEvent

interface NewsRepository {

    suspend fun loadUpcomingEvents(): List<SpaceEvent>

    suspend fun loadArticles(number: Int): List<SpaceArticle>
}