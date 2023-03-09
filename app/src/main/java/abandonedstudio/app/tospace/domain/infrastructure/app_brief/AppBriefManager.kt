package abandonedstudio.app.tospace.domain.infrastructure.app_brief

import android.app.PendingIntent

interface AppBriefManager {

    fun start()

    fun stop()

    val intentStart: PendingIntent

    val intentStop: PendingIntent
}