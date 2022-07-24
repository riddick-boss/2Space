package abandonedstudio.app.tospace.core.service.app_brief_voice_assistant_service.tts

import abandonedstudio.app.tospace.core.domain.util.DateFormat
import kotlin.math.abs

data class Launch(private val name: String, private val description: String?, private val timeStamp: DateFormat?, private val timeStampMillis: Long?) {

    private val now: Long get() = System.currentTimeMillis()

    private val _description: String = description.orEmpty()

    private val _timeStamp: String = timeStamp?.let { "Scheduled for ${it.day} ${it.monthName} ${it.yearShort}, ${it.hoursAndMinutes}" }.orEmpty()

    private val _fromNow: String = timeStampMillis?.let {
        var time = abs(now - timeStampMillis) / 1000 // from millis to seconds
        val days = abs(time / 86_400).let { if (it <= 0) "" else " $it days " } // 60sec * 60min * 24h
        time %= 86_400
        val hours = abs(time / 3600).let { if (it <= 0) "" else " $it hours " } // 60sec * 60min
        time %= 3600
        val minutes = abs(time / 60).let { if (it <= 0) "" else " $it minutes " }
        ", which is $days $hours $minutes from now"
    }.orEmpty()

    val tts: String = "... Upcoming launch is: ...  $name. $_timeStamp $_fromNow. Details: $_description ..."
}
