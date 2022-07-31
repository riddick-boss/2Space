package abandonedstudio.app.tospace.core.data.repository.news

import abandonedstudio.app.tospace.core.data.remote.news.articles.ArticlesRemoteApi
import abandonedstudio.app.tospace.core.data.remote.news.events.EventsRemoteApi
import abandonedstudio.app.tospace.core.domain.model.news.SpaceArticle
import abandonedstudio.app.tospace.core.domain.model.news.SpaceEvent
import abandonedstudio.app.tospace.core.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val eventsApi: EventsRemoteApi,
    private val articlesApi: ArticlesRemoteApi
) : NewsRepository {

    private fun fckRussia(title: String?): Boolean =
        !(title?.contains("russian", ignoreCase = true) ?: false)

    override suspend fun loadUpcomingEvents(): List<SpaceEvent> =
        eventsApi.loadUpcomingEvents().toSpaceEvents()
            .filter { fckRussia(it.title) } // boycott! stay strong ukraine!

    override suspend fun loadArticles(number: Int): List<SpaceArticle> =
        articlesApi.loadArticles(number).toSpaceArticles()
            .filter { fckRussia(it.title) } // boycott! stay strong ukraine!
}