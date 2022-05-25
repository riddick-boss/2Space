package abandonedstudio.app.tospace.core.data.remote.spacex.dto.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class UpcomingSpaceXLaunchesResponse(
    @SerialName("docs")
    val docs: List<Doc>,
    @SerialName("hasNextPage")
    val hasNextPage: Boolean? = null,
    @SerialName("hasPrevPage")
    val hasPrevPage: Boolean? = null,
    @SerialName("limit")
    val limit: Int? = null,
    @SerialName("nextPage")
    val nextPage: Int? = null,
    @SerialName("page")
    val page: Int? = null,
    @SerialName("pagingCounter")
    val pagingCounter: Int? = null,
    @SerialName("prevPage")
    val prevPage: Int? = null,
    @SerialName("totalDocs")
    val totalDocs: Int? = null,
    @SerialName("totalPages")
    val totalPages: Int? = null
) {
    @Keep
    @Serializable
    data class Doc(
        @SerialName("auto_update")
        val autoUpdate: Boolean? = null,
        @SerialName("capsules")
        val capsules: List<String?>? = null,
        @SerialName("cores")
        val cores: List<Core?>? = null,
        @SerialName("date_local")
        val dateLocal: String? = null,
        @SerialName("date_precision")
        val datePrecision: String? = null,
        @SerialName("date_unix")
        val dateUnix: Int? = null,
        @SerialName("date_utc")
        val dateUtc: String? = null,
        @SerialName("details")
        val details: String? = null,
        @SerialName("flight_number")
        val flightNumber: Int? = null,
        @SerialName("id")
        val id: String? = null,
        @SerialName("launch_library_id")
        val launchLibraryId: String? = null,
        @SerialName("launchpad")
        val launchpad: String? = null,
        @SerialName("links")
        val links: Links? = null,
        @SerialName("name")
        val name: String? = null,
        @SerialName("net")
        val net: Boolean? = null,
        @SerialName("payloads")
        val payloads: List<String?>? = null,
        @SerialName("rocket")
        val rocket: Rocket? = null,
        @SerialName("static_fire_date_unix")
        val staticFireDateUnix: Int? = null,
        @SerialName("static_fire_date_utc")
        val staticFireDateUtc: String? = null,
        @SerialName("success")
        val success: Boolean? = null,
        @SerialName("tbd")
        val tbd: Boolean? = null,
        @SerialName("upcoming")
        val upcoming: Boolean? = null
    ) {
        @Keep
        @Serializable
        data class Core(
            @SerialName("core")
            val core: String? = null,
            @SerialName("flight")
            val flight: Int? = null,
            @SerialName("gridfins")
            val gridfins: Boolean? = null,
            @SerialName("reused")
            val reused: Boolean? = null
        )

        @Keep
        @Serializable
        data class Links(
            @SerialName("article")
            val article: String? = null,
            @SerialName("flickr")
            val flickr: Flickr? = null,
            @SerialName("patch")
            val patch: Patch? = null,
            @SerialName("presskit")
            val presskit: String? = null,
            @SerialName("reddit")
            val reddit: Reddit? = null,
            @SerialName("webcast")
            val webcast: String? = null,
            @SerialName("wikipedia")
            val wikipedia: String? = null,
            @SerialName("youtube_id")
            val youtubeId: String? = null
        ) {
            @Keep
            @Serializable
            data class Flickr(
                @SerialName("original")
                val original: List<String?>? = null,
                @SerialName("small")
                val small: List<String?>? = null
            )

            @Keep
            @Serializable
            data class Patch(
                @SerialName("large")
                val large: String? = null,
                @SerialName("small")
                val small: String? = null
            )

            @Keep
            @Serializable
            data class Reddit(
                @SerialName("campaign")
                val campaign: String? = null,
                @SerialName("launch")
                val launch: String? = null,
                @SerialName("media")
                val media: String? = null,
                @SerialName("recovery")
                val recovery: String? = null
            )
        }

        @Keep
        @Serializable
        data class Rocket(
            @SerialName("id")
            val id: String? = null,
            @SerialName("name")
            val name: String? = null
        )
    }
}