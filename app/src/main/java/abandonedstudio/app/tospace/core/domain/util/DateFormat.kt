package abandonedstudio.app.tospace.core.domain.util

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.domain.util.resources.StringUtil
import java.text.SimpleDateFormat
import java.util.*

class DateFormat(private val timeStamp: Int) {

    enum class Precision {
        MONTH, YEAR
    }

    val day: String
        get() = SimpleDateFormat("dd", Locale.getDefault()).format(Date(timeStamp * 1000L))

    val month: String
        get() = SimpleDateFormat("MM", Locale.getDefault()).format(Date(timeStamp * 1000L))

    val year: String
        get() = SimpleDateFormat("yyyy", Locale.getDefault()).format(Date(timeStamp * 1000L))

    val monthAndYear: String
        get() = SimpleDateFormat("MM/yyyy", Locale.getDefault()).format(Date(timeStamp * 1000L))

    fun format(precision: Precision): String = try {
        val format = when (precision) {
            Precision.MONTH -> SimpleDateFormat("dd/MM', 'HH:mm", Locale.getDefault())
            Precision.YEAR -> SimpleDateFormat("dd/MM/yy', 'HH:mm", Locale.getDefault())
        }
        format.format(Date(timeStamp * 1000L))
    } catch (e: Exception) {
        StringUtil.getString(R.string.no_data_info)
    }
}