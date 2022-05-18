package abandonedstudio.app.tospace.core.domain.util

import java.text.SimpleDateFormat
import java.util.*

enum class Precision {
    MONTH, YEAR
}

fun Int.formatDateFromUnix(precision: Precision): String =
    try {
        val format = when(precision) {
            Precision.MONTH -> SimpleDateFormat("dd/MM', 'HH:mm", Locale.getDefault())
            Precision.YEAR -> SimpleDateFormat("dd/MM/yy', 'HH:mm", Locale.getDefault())
        }
        format.format(Date(this * 1000L))
    } catch (e: Exception) {
        "No data"
    }