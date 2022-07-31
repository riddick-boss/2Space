package abandonedstudio.app.tospace.features.news.domain

import abandonedstudio.app.tospace.core.domain.repository.NewsRepository
import abandonedstudio.app.tospace.features.news.data.Article
import abandonedstudio.app.tospace.features.news.data.Event
import javax.inject.Inject

class DataSource @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend fun loadUpcomingEvents(): List<Event> =
        newsRepository.loadUpcomingEvents().map {
            Event(
                title = it.title,
                description = it.description,
                imageUrl = it.imageUrl,
                newsUrl = it.newsUrl,
                videoUrl = it.videoUrl,
                type = it.type
            )
        }

    suspend fun loadArticles(number: Int): List<Article> =
        newsRepository.loadArticles(number).map {
            Article(
                title = it.title,
                summary = it.summary,
                imageUrl = it.imageUrl,
                url = it.url
            )
        }
}