package abandonedstudio.app.tospace.core.domain.repository

import abandonedstudio.app.tospace.core.domain.model.news.SpaceArticle
import abandonedstudio.app.tospace.core.domain.model.news.SpaceEvent

interface NewsRepository {

    suspend fun loadUpcomingEvents(): List<SpaceEvent>

    @Deprecated(message = "Use newer fun", replaceWith = ReplaceWith(" loadArticles(number: Int)"))
    suspend fun loadArticles(): List<SpaceArticle>

    suspend fun loadArticles(number: Int): List<SpaceArticle>
}