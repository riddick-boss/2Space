package abandonedstudio.app.tospace.core.data.remote.launches.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class LaunchDetailedResponse(
    @SerialName("agency_launch_attempt_count")
    val agencyLaunchAttemptCount: Int? = null,
    @SerialName("agency_launch_attempt_count_year")
    val agencyLaunchAttemptCountYear: Int? = null,
    @SerialName("failreason")
    val failreason: String? = null,
    @SerialName("hashtag")
    val hashtag: String? = null,
    @SerialName("holdreason")
    val holdreason: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("image")
    val image: String? = null,
    @SerialName("infographic")
    val infographic: String? = null,
    @SerialName("last_updated")
    val lastUpdated: String? = null,
    @SerialName("launch_service_provider")
    val launchServiceProvider: LaunchServiceProvider? = null,
    @SerialName("location_launch_attempt_count")
    val locationLaunchAttemptCount: Int? = null,
    @SerialName("location_launch_attempt_count_year")
    val locationLaunchAttemptCountYear: Int? = null,
    @SerialName("mission")
    val mission: Mission? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("net")
    val net: String? = null,
    @SerialName("orbital_launch_attempt_count")
    val orbitalLaunchAttemptCount: Int? = null,
    @SerialName("orbital_launch_attempt_count_year")
    val orbitalLaunchAttemptCountYear: Int? = null,
    @SerialName("pad")
    val pad: Pad? = null,
    @SerialName("pad_launch_attempt_count")
    val padLaunchAttemptCount: Int? = null,
    @SerialName("pad_launch_attempt_count_year")
    val padLaunchAttemptCountYear: Int? = null,
    @SerialName("probability")
    val probability: Int? = null,
    @SerialName("program")
    val program: List<Program?>? = null,
    @SerialName("rocket")
    val rocket: Rocket? = null,
    @SerialName("slug")
    val slug: String? = null,
    @SerialName("status")
    val status: Status? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("vidURLs")
    val vidURLs: List<VidURL?>? = null,
    @SerialName("webcast_live")
    val webcastLive: Boolean? = null,
    @SerialName("window_end")
    val windowEnd: String? = null,
    @SerialName("window_start")
    val windowStart: String? = null,
    @SerialName("mission_patches")
    val mission_patches: List<MissionPatch?>? = null
) {
    @Keep
    @Serializable
    data class LaunchServiceProvider(
        @SerialName("id")
        val id: Int? = null,
        @SerialName("name")
        val name: String? = null,
        @SerialName("type")
        val type: String? = null,
        @SerialName("url")
        val url: String? = null
    )

    @Keep
    @Serializable
    data class Mission(
        @SerialName("description")
        val description: String? = null,
        @SerialName("id")
        val id: Int? = null,
        @SerialName("launch_designator")
        val launchDesignator: String? = null,
        @SerialName("name")
        val name: String? = null,
        @SerialName("orbit")
        val orbit: Orbit? = null,
        @SerialName("type")
        val type: String? = null
    ) {
        @Keep
        @Serializable
        data class Orbit(
            @SerialName("abbrev")
            val abbrev: String? = null,
            @SerialName("id")
            val id: Int? = null,
            @SerialName("name")
            val name: String? = null
        )
    }

    @Keep
    @Serializable
    data class Pad(
        @SerialName("agency_id")
        val agencyId: Int? = null,
        @SerialName("id")
        val id: Int? = null,
        @SerialName("info_url")
        val infoUrl: String? = null,
        @SerialName("latitude")
        val latitude: String? = null,
        @SerialName("location")
        val location: Location? = null,
        @SerialName("longitude")
        val longitude: String? = null,
        @SerialName("map_image")
        val mapImage: String? = null,
        @SerialName("map_url")
        val mapUrl: String? = null,
        @SerialName("name")
        val name: String? = null,
        @SerialName("orbital_launch_attempt_count")
        val orbitalLaunchAttemptCount: Int? = null,
        @SerialName("total_launch_count")
        val totalLaunchCount: Int? = null,
        @SerialName("url")
        val url: String? = null,
        @SerialName("wiki_url")
        val wikiUrl: String? = null
    ) {
        @Keep
        @Serializable
        data class Location(
            @SerialName("country_code")
            val countryCode: String? = null,
            @SerialName("id")
            val id: Int? = null,
            @SerialName("map_image")
            val mapImage: String? = null,
            @SerialName("name")
            val name: String? = null,
            @SerialName("total_landing_count")
            val totalLandingCount: Int? = null,
            @SerialName("total_launch_count")
            val totalLaunchCount: Int? = null,
            @SerialName("url")
            val url: String? = null
        )
    }

    @Keep
    @Serializable
    data class Program(
        @SerialName("agencies")
        val agencies: List<Agency?>? = null,
        @SerialName("description")
        val description: String? = null,
        @SerialName("end_date")
        val endDate: String? = null,
        @SerialName("id")
        val id: Int? = null,
        @SerialName("image_url")
        val imageUrl: String? = null,
        @SerialName("info_url")
        val infoUrl: String? = null,
        @SerialName("mission_patches")
        val missionPatches: List<MissionPatche?>? = null,
        @SerialName("name")
        val name: String? = null,
        @SerialName("start_date")
        val startDate: String? = null,
        @SerialName("url")
        val url: String? = null,
        @SerialName("wiki_url")
        val wikiUrl: String? = null
    ) {
        @Keep
        @Serializable
        data class Agency(
            @SerialName("id")
            val id: Int? = null,
            @SerialName("name")
            val name: String? = null,
            @SerialName("type")
            val type: String? = null,
            @SerialName("url")
            val url: String? = null
        )

        @Keep
        @Serializable
        data class MissionPatche(
            @SerialName("agency")
            val agency: Agency? = null,
            @SerialName("id")
            val id: Int? = null,
            @SerialName("image_url")
            val imageUrl: String? = null,
            @SerialName("name")
            val name: String? = null,
            @SerialName("priority")
            val priority: Int? = null
        ) {
            @Keep
            @Serializable
            data class Agency(
                @SerialName("id")
                val id: Int? = null,
                @SerialName("name")
                val name: String? = null,
                @SerialName("type")
                val type: String? = null,
                @SerialName("url")
                val url: String? = null
            )
        }
    }

    @Keep
    @Serializable
    data class Rocket(
        @SerialName("configuration")
        val configuration: Configuration? = null,
        @SerialName("id")
        val id: Int? = null
    ) {
        @Keep
        @Serializable
        data class Configuration(
            @SerialName("family")
            val family: String? = null,
            @SerialName("full_name")
            val fullName: String? = null,
            @SerialName("id")
            val id: Int? = null,
            @SerialName("name")
            val name: String? = null,
            @SerialName("url")
            val url: String? = null,
            @SerialName("variant")
            val variant: String? = null
        )
    }

    @Keep
    @Serializable
    data class Status(
        @SerialName("abbrev")
        val abbrev: String? = null,
        @SerialName("description")
        val description: String? = null,
        @SerialName("id")
        val id: Int? = null,
        @SerialName("name")
        val name: String? = null
    )

    @Keep
    @Serializable
    data class VidURL(
        @SerialName("description")
        val description: String? = null,
        @SerialName("feature_image")
        val featureImage: String? = null,
        @SerialName("priority")
        val priority: Int? = null,
        @SerialName("title")
        val title: String? = null,
        @SerialName("url")
        val url: String? = null
    )

    @Keep
    @Serializable
    data class MissionPatch(
        @SerialName("id")
        val id: Int? = null,
        @SerialName("name")
        val name: String? = null,
        @SerialName("priority")
        val priority: Int? = null,
        @SerialName("image_url")
        val image_url: String? = null,
        @SerialName("url")
        val agency: Agency? = null
    ) {
        @Keep
        @Serializable
        data class Agency(
            @SerialName("id")
            val id: Int? = null,
            @SerialName("name")
            val name: String? = null,
            @SerialName("url")
            val url: String? = null,
            @SerialName("type")
            val type: String? = null,
        )
    }
}