package abandonedstudio.app.tospace.core.data.repository.news

import abandonedstudio.app.tospace.core.data.remote.news.articles.dto.ArticlesResponseItem
import abandonedstudio.app.tospace.core.data.remote.news.events.dto.TheSpaceDevsEventResponse
import abandonedstudio.app.tospace.domain.model.news.SpaceArticle
import abandonedstudio.app.tospace.domain.model.news.SpaceEvent

fun TheSpaceDevsEventResponse.toSpaceEvents(): List<SpaceEvent> =
    this.results?.map {
        SpaceEvent(
            title = it?.name,
            description = it?.description,
            imageUrl = it?.featureImage,
            newsUrl = it?.newsUrl,
            videoUrl = it?.videoUrl,
            type = it?.type?.name
        )
    } ?: emptyList()

fun ArticlesResponseItem.toSpaceArticles() =
    this.let { response ->
        response.results?.map {
            SpaceArticle(
                title = it?.title,
                summary = it?.summary,
                imageUrl = it?.imageUrl,
                url = it?.url
            )
        } ?: emptyList()
    }