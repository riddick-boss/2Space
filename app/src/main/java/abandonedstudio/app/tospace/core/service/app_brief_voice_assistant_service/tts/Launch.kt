package abandonedstudio.app.tospace.core.service.app_brief_voice_assistant_service.tts

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.domain.util.DateFormat
import abandonedstudio.app.tospace.di.ToSpaceApplication
import kotlin.math.abs

data class Launch(private val name: String, private val description: String?, private val timeStamp: DateFormat?, private val timeStampMillis: Long?) {

    private val now: Long get() = System.currentTimeMillis()

    private val _description: String = description ?: ToSpaceApplication.resources.getString(R.string.app_brief_tts_launch_no_description)

    private val _timeStamp: String = timeStamp?.let { ToSpaceApplication.resources.getString(R.string.app_brief_tts_launch_timestamp, it.day, it.monthName, it.yearShort, it.hoursAndMinutes) }.orEmpty()

    private val _fromNow: String = timeStampMillis?.let {
        var time = abs(now - timeStampMillis) / 1000 // from millis to seconds
        val days = abs(time / 86_400).let { if (it <= 0) "" else ToSpaceApplication.resources.getString(R.string.app_brief_tts_launch_from_now_days, it) } // 60sec * 60min * 24h
        time %= 86_400
        val hours = abs(time / 3600).let { if (it <= 0) "" else ToSpaceApplication.resources.getString(R.string.app_brief_tts_launch_from_now_hours, it) } // 60sec * 60min
        time %= 3600
        val minutes = abs(time / 60).let { if (it <= 0) "" else ToSpaceApplication.resources.getString(R.string.app_brief_tts_launch_from_now_minutes, it) }
        ToSpaceApplication.resources.getString(R.string.app_brief_tts_launch_from_now, days, hours, minutes)
    }.orEmpty()

    val tts: String = ToSpaceApplication.resources.getString(R.string.app_brief_tts_launch_tts, name, _timeStamp, _fromNow, _description)
}
