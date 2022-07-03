package abandonedstudio.app.tospace.core.presentation.notification

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.service.fcm.FCMServiceAdapter
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AllLaunchesNotificationCenter @Inject constructor(
    @ApplicationContext private val context: Context,
    private val notificationManagerCompat: NotificationManagerCompat,
    fcmServiceAdapter: FCMServiceAdapter
) {

    val start: Flow<Unit> by lazy {
        fcmServiceAdapter
            .onMessageReceived
            .map {
                val notificationId = it.data[ALL_LAUNCHES_NOTIFICATION_ID]
                val title = it.notification?.title
                val body = it.notification?.body

                if (notificationId == null || title.isNullOrBlank() || body.isNullOrBlank()) {
                    null
                } else {
                    Triple(notificationId, title, body)
                }
            }
            .filterNotNull()
            .onEach { (messageId, title, body) ->
                showNotification(messageId, title, body)
            }
            .map {  }
    }

    private fun createNotification(title: String, body: String): Notification = NotificationCompat
        .Builder(context, NotificationConstants.ALL_LAUNCHES_NOTIFICATION_CHANNEL_ID)
        .setContentTitle(title)
        .setContentText(body)
        .setSmallIcon(R.drawable.rocket_icon)
        .setDefaults(Notification.DEFAULT_ALL)
        .setOngoing(false)
        .setAutoCancel(true)
        .setShowWhen(true)
        .build()

    private fun showNotification(id: String, title: String, body: String) {
        val notification = createNotification(title, body)
        notificationManagerCompat.notify(id, NotificationConstants.ALL_LAUNCHES_NOTIFICATION_ID, notification)
    }

    private fun registerNotificationChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return
        }
        val channel = NotificationChannel(
            NotificationConstants.ALL_LAUNCHES_NOTIFICATION_CHANNEL_ID,
            context.getString(R.string.notification_all_launches_channel_name),
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManagerCompat.createNotificationChannel(channel)
    }

    companion object {
        private const val ALL_LAUNCHES_NOTIFICATION_ID = "all_launches_notification_id"
    }

    init {
        registerNotificationChannel()
    }
}