package abandonedstudio.app.tospace.core.domain.model.launches

import abandonedstudio.app.tospace.core.domain.util.DateFormat

data class Launch(
    val id: String?,
    val name: String?,
    val status: LaunchStatus?,
    val launchPad: LaunchPad?,
    val description: String?,
    val imageUrl: String?,
    val infographicUrl: String?,
    val probability: Int?,
    val timeStampMillis: Long?
) {

    data class LaunchStatus(
        val id: Int?,
        val name: String?,
        val shortName: String?,
        val description: String?
    )

    data class LaunchPad(
        val name: String?,
        val location: String?
    )

    val timeStamp: DateFormat? = timeStampMillis?.let { DateFormat(it) }
}