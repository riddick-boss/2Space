package abandonedstudio.app.tospace.features.news.domain

import abandonedstudio.app.tospace.core.domain.repository.NewsRepository
import abandonedstudio.app.tospace.features.news.data.Event
import javax.inject.Inject

class DataSource @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend fun loadUpcomingLaunches(): List<Event> =
        newsRepository.loadUpcomingEvents().map {
            Event(
                name = it.name,
                description = it.description,
                imageUrl = it.imageUrl,
                newsUrl = it.newsUrl,
                videoUrl = it.videoUrl,
                type = it.type
            )
        }
}