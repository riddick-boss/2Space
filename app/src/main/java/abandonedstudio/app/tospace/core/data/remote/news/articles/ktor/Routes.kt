package abandonedstudio.app.tospace.core.data.remote.news.articles.ktor

object Routes {

    private const val BASE_URL = "https://api.spaceflightnewsapi.net/v3/"

    const val ARTICLES_URL = "${BASE_URL}articles?_limit=40"

    fun articlesUrl(articlesNumber: Int): String =
        "${BASE_URL}articles?_limit=$articlesNumber"
}