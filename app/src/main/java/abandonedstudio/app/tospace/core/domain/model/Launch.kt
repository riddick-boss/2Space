package abandonedstudio.app.tospace.core.domain.model

data class Launch(
    val missionName: String?,
    val logoImgPath: String?,
    val rocket: String?,
    val flightNumber: Int?,
    val timeStamp: Int?,
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
        val massKg: Int?,
        val orbit: String?,
        val inclination: Double?,
        val periodMin: Double?,
        val customers: List<String?>
    )
}
