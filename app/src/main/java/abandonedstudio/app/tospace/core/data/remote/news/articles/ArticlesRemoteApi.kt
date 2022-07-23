package abandonedstudio.app.tospace.core.data.remote.news.articles

import abandonedstudio.app.tospace.core.data.remote.news.articles.dto.ArticlesResponseItem

interface ArticlesRemoteApi {

    @Deprecated(message = "Use newer fun", replaceWith = ReplaceWith("loadArticles(number: Int)"))
    suspend fun loadArticles(): List<ArticlesResponseItem>

    suspend fun loadArticles(number: Int): List<ArticlesResponseItem>
}