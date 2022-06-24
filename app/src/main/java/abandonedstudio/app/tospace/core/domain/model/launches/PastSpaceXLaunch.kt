package abandonedstudio.app.tospace.core.domain.model.launches

import abandonedstudio.app.tospace.core.domain.util.DateFormat

data class PastSpaceXLaunch(
    val missionName: String?,
    val logoImgPath: String?,
    val rocket: String?,
    val timeStamp: Long?,
    val links: Links,
    val details: String?,
    val launchPad: String?,
    val missionSuccess: Boolean?,
    val landingSuccess: Boolean?,
    val fairingsRecovered: Boolean?,
    val core: Core,
    val landPad: LandPad,
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
        val apoapsisKm: Double?,
        val periapsisKm: Double?,
        val customers: List<String?>
    )

    data class LandPad(
        val name: String?,
        val fullName: String?,
        val region: String?
    )

    data class Core(
        val reused: Boolean?,
        val flightNum: Int?,
    )

    val date: String?
        get() = timeStamp?.let { DateFormat(it).format(DateFormat.Precision.YEAR) }
}
