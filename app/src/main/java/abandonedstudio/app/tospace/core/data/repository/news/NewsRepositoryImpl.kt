package abandonedstudio.app.tospace.core.data.repository.news

import abandonedstudio.app.tospace.core.data.remote.news.articles.ArticlesRemoteApi
import abandonedstudio.app.tospace.core.data.remote.news.events.EventsRemoteApi
import abandonedstudio.app.tospace.domain.model.news.SpaceArticle
import abandonedstudio.app.tospace.domain.model.news.SpaceEvent
import abandonedstudio.app.tospace.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val eventsApi: EventsRemoteApi,
    private val articlesApi: ArticlesRemoteApi,
    private val mapper: NewsMapper
) : NewsRepository {

    private fun FCK_russia(title: String?): Boolean =
        !(title?.contains("russia", ignoreCase = true) ?: false)

    override suspend fun loadUpcomingEvents(): List<SpaceEvent> {
        val events = eventsApi.loadUpcomingEvents()
        return mapper.toSpaceEvents(events).filter { FCK_russia(it.title) } // fck rus! stay strong Ukraine!
    }


    override suspend fun loadArticles(number: Int): List<SpaceArticle> {
        val articles = articlesApi.loadArticles(number)
        return mapper.toSpaceArticles(articles).filter { FCK_russia(it.title) } // fck rus! stay strong Ukraine!
    }
}