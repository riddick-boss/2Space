package abandonedstudio.app.tospace.core.domain.model

import abandonedstudio.app.tospace.core.domain.util.DateFormat

data class UpcomingSpaceXLaunch(
    val missionName: String?,
    val logoImgPath: String?,
    val rocket: String?,
    val timeStamp: Int?,
    private val precisionFlag: String?
) {
    private val precision: Precision
        get() = precisionFlag.toPrecision()

    val net: Boolean
        get() = precision != Precision.DEFINED

    val date: String?
        get() {
            if (timeStamp == null) return null

            val dateFormat = DateFormat(timeStamp)

            return when (precision) {
                Precision.DEFINED -> dateFormat.format(DateFormat.Precision.YEAR)
                Precision.MONTH -> dateFormat.monthAndYear
                Precision.UNDEFINED -> dateFormat.year
            }
        }

    private enum class Precision {
        DEFINED, MONTH, UNDEFINED
    }

    private fun String?.toPrecision() =
        when (this) {
        "month" -> Precision.MONTH
        "day" -> Precision.DEFINED
        "hour" -> Precision.DEFINED
        else -> Precision.UNDEFINED
    }
}
