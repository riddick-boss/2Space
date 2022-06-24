package abandonedstudio.app.tospace.core.domain.model.launches

import abandonedstudio.app.tospace.core.domain.util.DateFormat

data class UpcomingSpaceXLaunch(
    val missionName: String?,
    val logoImgPath: String?,
    val rocket: String?,
    val timeStamp: Long?,
    private val precisionFlag: String?
) {
    private val precision: Precision
        get() = precisionFlag.toPrecision()

    val net: Boolean
        get() = precision != Precision.DEFINED

    val date: String?
        get() =
            timeStamp?.let {
                val dateFormat = DateFormat(timeStamp)

                when (precision) {
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
