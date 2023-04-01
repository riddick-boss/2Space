package abandonedstudio.app.tospace.core.data.repository.news

import abandonedstudio.app.tospace.core.data.remote.news.articles.dto.ArticlesResponseItem
import abandonedstudio.app.tospace.core.data.remote.news.events.dto.TheSpaceDevsEventResponse
import abandonedstudio.app.tospace.domain.model.news.SpaceArticle
import abandonedstudio.app.tospace.domain.model.news.SpaceEvent
import javax.inject.Inject

class NewsMapper @Inject constructor() {
    fun toSpaceEvents(response: TheSpaceDevsEventResponse): List<SpaceEvent> = response.let { resp ->
        resp.results?.map {
            SpaceEvent(
                title = it?.name,
                description = it?.description,
                imageUrl = it?.featureImage,
                newsUrl = it?.newsUrl,
                videoUrl = it?.videoUrl,
                type = it?.type?.name
            )
        } ?: emptyList()
    }

    fun toSpaceArticles(response: List<ArticlesResponseItem>) = response.let { resp ->
        resp.map {
            SpaceArticle(
                title = it.title,
                summary = it.summary,
                imageUrl = it.imageUrl,
                url = it.url
            )
        }
    }
}