package abandonedstudio.app.tospace.core.data.remote.news.articles

import abandonedstudio.app.tospace.core.data.remote.news.articles.dto.ArticlesResponseItem

interface ArticlesRemoteApi {

    suspend fun loadArticles(): List<ArticlesResponseItem>
}