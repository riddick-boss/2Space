package abandonedstudio.app.tospace.domain.model.launches

data class DetailedLaunch(
    val missionName: String?,
    val logoImgPath: String?,
    val rocket: String?,
    val flightNumber: Int?,
    val timeStamp: Long?,
    val links: Links,
    val details: String?,
    val launchPad: String?,
    val payloads: List<Payload>
) {
    data class Links(
        val wikipedia: String?,
        val yt: String?,
        val reddit: String?
    )

    data class Payload(
        val type: String?,
        val massKg: Double?,
        val orbit: String?,
        val inclination: Double?,
        val periodMin: Double?,
        val customers: List<String?>
    )
}
