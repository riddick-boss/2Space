package abandonedstudio.app.tospace.core.data.remote.news.articles.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class ArticlesResponseItem(
    @SerialName("count")
    val count: Int? = null,
    @SerialName("next")
    val next: String? = null,
    @SerialName("previous")
    val previous: String? = null,
    @SerialName("results")
    val results: List<Result?>? = null
) {
    @Keep
    @Serializable
    data class Result(
        @SerialName("featured")
        val featured: Boolean? = null,
        @SerialName("id")
        val id: Int? = null,
        @SerialName("image_url")
        val imageUrl: String? = null,
        @SerialName("news_site")
        val newsSite: String? = null,
        @SerialName("published_at")
        val publishedAt: String? = null,
        @SerialName("summary")
        val summary: String? = null,
        @SerialName("title")
        val title: String? = null,
        @SerialName("updated_at")
        val updatedAt: String? = null,
        @SerialName("url")
        val url: String? = null
    )
}