package abandonedstudio.app.tospace.core.data.remote.news.articles.ktor

import abandonedstudio.app.tospace.BuildConfig

object Routes {

    fun articlesUrl(articlesNumber: Int): String =
        "${BuildConfig.SPACE_FLIGHT_NEWS_URL}articles/?limit=$articlesNumber&offset=0"
}