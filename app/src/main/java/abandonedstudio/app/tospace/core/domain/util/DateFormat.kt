package abandonedstudio.app.tospace.core.domain.util

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.domain.util.resources.StringUtil
import java.text.SimpleDateFormat
import java.util.*

class DateFormat(timeStampMillis: Long) { // this class may be open in future if needed

    enum class Precision {
        MONTH, YEAR
    }

    private val locale = try {
        Locale.getDefault()
    } catch (e: Exception) {
        Locale.US
    }

    private val date = Date(timeStampMillis)

    val hoursAndMinutes: String
        get() = SimpleDateFormat("HH:mm", Locale.US).format(date)

    val dayOfWeekShort: String
        get() = SimpleDateFormat("EEE", Locale.US).format(date)

    val dayOfWeekFull: String
        get() = SimpleDateFormat("EEEE", Locale.US).format(date)

    val day: String
        get() = SimpleDateFormat("dd", locale).format(date)

    val month: String
        get() = SimpleDateFormat("MM", locale).format(date)

    val monthName: String
        get() = SimpleDateFormat("MMMM", Locale.US).format(date)

    val year: String
        get() = SimpleDateFormat("yyyy", locale).format(date)

    val yearShort: String
        get() = SimpleDateFormat("yy", locale).format(date)

    val monthAndYear: String
        get() = SimpleDateFormat("MM/yyyy", locale).format(date)

    fun format(precision: Precision): String = try {
        val simpleDateFormat = when (precision) {
            Precision.MONTH -> SimpleDateFormat("dd/MM', 'HH:mm", locale)
            Precision.YEAR -> SimpleDateFormat("dd/MM/yy', 'HH:mm", locale)
        }
        simpleDateFormat.format(date)
    } catch (e: Exception) {
        StringUtil.getString(R.string.no_data_info)
    }
}