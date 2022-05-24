package abandonedstudio.app.tospace.core.data.remote.spacex.dto.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class PastSpaceXLaunchesResponse(
    @SerialName("docs")
    val docs: List<Doc?>? = null,
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
        @SerialName("crew")
        val crew: List<Crew?>? = null,
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
        @SerialName("fairings")
        val fairings: Fairings? = null,
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
        val payloads: List<Payload?>? = null,
        @SerialName("rocket")
        val rocket: Rocket? = null,
        @SerialName("ships")
        val ships: List<String?>? = null,
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
            @SerialName("landing_attempt")
            val landingAttempt: Boolean? = null,
            @SerialName("landing_success")
            val landingSuccess: Boolean? = null,
            @SerialName("landing_type")
            val landingType: String? = null,
            @SerialName("landpad")
            val landpad: Landpad? = null,
            @SerialName("legs")
            val legs: Boolean? = null,
            @SerialName("reused")
            val reused: Boolean? = null
        ) {
            @Keep
            @Serializable
            data class Landpad(
                @SerialName("details")
                val details: String? = null,
                @SerialName("full_name")
                val fullName: String? = null,
                @SerialName("id")
                val id: String? = null,
                @SerialName("images")
                val images: Images? = null,
                @SerialName("landing_attempts")
                val landingAttempts: Int? = null,
                @SerialName("landing_successes")
                val landingSuccesses: Int? = null,
                @SerialName("latitude")
                val latitude: Double? = null,
                @SerialName("launches")
                val launches: List<String?>? = null,
                @SerialName("locality")
                val locality: String? = null,
                @SerialName("longitude")
                val longitude: Double? = null,
                @SerialName("name")
                val name: String? = null,
                @SerialName("region")
                val region: String? = null,
                @SerialName("status")
                val status: String? = null,
                @SerialName("type")
                val type: String? = null,
                @SerialName("wikipedia")
                val wikipedia: String? = null
            ) {
                @Keep
                @Serializable
                data class Images(
                    @SerialName("large")
                    val large: List<String?>? = null
                )
            }
        }

        @Keep
        @Serializable
        data class Crew(
            @SerialName("crew")
            val crew: String? = null,
            @SerialName("role")
            val role: String? = null
        )

        @Keep
        @Serializable
        data class Fairings(
            @SerialName("recovered")
            val recovered: Boolean? = null,
            @SerialName("recovery_attempt")
            val recoveryAttempt: Boolean? = null,
            @SerialName("reused")
            val reused: Boolean? = null,
            @SerialName("ships")
            val ships: List<String?>? = null
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
        data class Payload(
            @SerialName("apoapsis_km")
            val apoapsisKm: Double? = null,
            @SerialName("arg_of_pericenter")
            val argOfPericenter: Double? = null,
            @SerialName("customers")
            val customers: List<String?>? = null,
            @SerialName("dragon")
            val dragon: Dragon? = null,
            @SerialName("eccentricity")
            val eccentricity: Double? = null,
            @SerialName("epoch")
            val epoch: String? = null,
            @SerialName("id")
            val id: String? = null,
            @SerialName("inclination_deg")
            val inclinationDeg: Double? = null,
            @SerialName("launch")
            val launch: String? = null,
            @SerialName("lifespan_years")
            val lifespanYears: Double? = null,
            @SerialName("longitude")
            val longitude: Double? = null,
            @SerialName("manufacturers")
            val manufacturers: List<String?>? = null,
            @SerialName("mass_kg")
            val massKg: Double? = null,
            @SerialName("mass_lbs")
            val massLbs: Double? = null,
            @SerialName("mean_anomaly")
            val meanAnomaly: Double? = null,
            @SerialName("mean_motion")
            val meanMotion: Double? = null,
            @SerialName("name")
            val name: String? = null,
            @SerialName("nationalities")
            val nationalities: List<String?>? = null,
            @SerialName("norad_ids")
            val noradIds: List<Int?>? = null,
            @SerialName("orbit")
            val orbit: String? = null,
            @SerialName("periapsis_km")
            val periapsisKm: Double? = null,
            @SerialName("period_min")
            val periodMin: Double? = null,
            @SerialName("raan")
            val raan: Double? = null,
            @SerialName("reference_system")
            val referenceSystem: String? = null,
            @SerialName("regime")
            val regime: String? = null,
            @SerialName("reused")
            val reused: Boolean? = null,
            @SerialName("semi_major_axis_km")
            val semiMajorAxisKm: Double? = null,
            @SerialName("type")
            val type: String? = null
        ) {
            @Keep
            @Serializable
            data class Dragon(
                @SerialName("capsule")
                val capsule: String? = null,
                @SerialName("flight_time_sec")
                val flightTimeSec: Double? = null,
                @SerialName("land_landing")
                val landLanding: Boolean? = null,
                @SerialName("manifest")
                val manifest: String? = null,
                @SerialName("mass_returned_kg")
                val massReturnedKg: Double? = null,
                @SerialName("mass_returned_lbs")
                val massReturnedLbs: Double? = null,
                @SerialName("water_landing")
                val waterLanding: Boolean? = null
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