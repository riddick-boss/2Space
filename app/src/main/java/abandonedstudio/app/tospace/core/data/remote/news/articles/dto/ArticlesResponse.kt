package abandonedstudio.app.tospace.core.data.remote.news.articles.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class ArticlesResponseItem(
    @SerialName("events")
    val events: List<Event?>? = null,
    @SerialName("featured")
    val featured: Boolean? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("imageUrl")
    val imageUrl: String? = null,
    @SerialName("launches")
    val launches: List<Launch?>? = null,
    @SerialName("newsSite")
    val newsSite: String? = null,
    @SerialName("publishedAt")
    val publishedAt: String? = null,
    @SerialName("summary")
    val summary: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("updatedAt")
    val updatedAt: String? = null,
    @SerialName("url")
    val url: String? = null
) {
    @Keep
    @Serializable
    data class Event(
        @SerialName("id")
        val id: Int? = null,
        @SerialName("provider")
        val provider: String? = null
    )

    @Keep
    @Serializable
    data class Launch(
        @SerialName("id")
        val id: String? = null,
        @SerialName("provider")
        val provider: String? = null
    )
}