package abandonedstudio.app.tospace.core.data.remote.news.articles.ktor

import abandonedstudio.app.tospace.core.data.remote.news.articles.ArticlesRemoteApi
import abandonedstudio.app.tospace.core.data.remote.news.articles.dto.ArticlesResponseItem
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import javax.inject.Inject

class KtorArticlesRemoteApi @Inject constructor(
    private val client: HttpClient
) : ArticlesRemoteApi {

    override suspend fun loadArticles(number: Int): ArticlesResponseItem =
        client.get(Routes.articlesUrl(number)).body()
}