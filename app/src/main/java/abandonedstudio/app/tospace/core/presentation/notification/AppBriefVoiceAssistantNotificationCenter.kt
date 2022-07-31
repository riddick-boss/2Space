package abandonedstudio.app.tospace.core.presentation.notification

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.service.app_brief_voice_assistant_service.AppBriefServiceManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppBriefVoiceAssistantNotificationCenter @Inject constructor(
    @ApplicationContext private val context: Context,
    private val notificationManagerCompat: NotificationManagerCompat,
    appBriefServiceManager: AppBriefServiceManager
) {

    val notificationId = NotificationConstants.APP_BRIEF_VOICE_ASSISTANT_SERVICE_NOTIFICATION_ID

    val notification: Notification = NotificationCompat
        .Builder(context, NotificationConstants.APP_BRIEF_VOICE_ASSISTANT_SERVICE_NOTIFICATION_CHANNEL_ID)
        .setContentTitle(context.getString(R.string.app_brief_voice_service_notification_title))
        .setContentText(context.getString(R.string.app_brief_voice_service_notification_body))
        .setSmallIcon(R.drawable.rocket_icon)
        .setDefaults(Notification.DEFAULT_ALL)
        .setOngoing(true)
        .setAutoCancel(false)
        .setShowWhen(false)
        .setSilent(true)
        .addAction(R.drawable.ic_twotone_stop_24, context.getString(R.string.app_brief_voice_service_notification_action_stop), appBriefServiceManager.intentStop)
        .build()

    private fun registerNotificationChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return

        val channel = NotificationChannel(
            NotificationConstants.APP_BRIEF_VOICE_ASSISTANT_SERVICE_NOTIFICATION_CHANNEL_ID,
            context.getString(R.string.notification_app_brief_voice_assistant_service_channel_name),
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManagerCompat.createNotificationChannel(channel)
    }

    init {
        registerNotificationChannel()
    }
}