package abandonedstudio.app.tospace.core.notification.center

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.domain.infrastructure.notification.NotificationProperties
import abandonedstudio.app.tospace.domain.infrastructure.app_brief.AppBriefManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppBriefVoiceAssistantNotificationCenter @Inject constructor(
    @ApplicationContext private val context: Context,
    notificationManagerCompat: NotificationManagerCompat,
    appBriefManager: AppBriefManager
) : DefaultNotificationCenter(notificationManagerCompat) {

    @RequiresApi(Build.VERSION_CODES.O)
    override val channel: NotificationChannel = NotificationChannel(
        NotificationProperties.APP_BRIEF_VOICE_ASSISTANT_SERVICE_NOTIFICATION_CHANNEL_ID,
        context.getString(R.string.notification_app_brief_voice_assistant_service_channel_name),
        NotificationManager.IMPORTANCE_DEFAULT
    )

    val notificationId = NotificationProperties.APP_BRIEF_VOICE_ASSISTANT_SERVICE_NOTIFICATION_ID

    val notification: Notification = NotificationCompat
        .Builder(context,
            NotificationProperties.APP_BRIEF_VOICE_ASSISTANT_SERVICE_NOTIFICATION_CHANNEL_ID
        )
        .setContentTitle(context.getString(R.string.app_brief_voice_service_notification_title))
        .setContentText(context.getString(R.string.app_brief_voice_service_notification_body))
        .setSmallIcon(R.drawable.rocket_icon)
        .setDefaults(Notification.DEFAULT_ALL)
        .setOngoing(true)
        .setAutoCancel(false)
        .setShowWhen(false)
        .setSilent(true)
        .addAction(R.drawable.ic_twotone_stop_24, context.getString(R.string.app_brief_voice_service_notification_action_stop), appBriefManager.intentStop)
        .build()

    init {
        registerNotificationChannel()
    }
}