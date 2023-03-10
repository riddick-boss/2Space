package abandonedstudio.app.tospace.core.notification.center

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.notification.push.FCMServiceAdapter
import abandonedstudio.app.tospace.domain.infrastructure.notification.NotificationProperties
import abandonedstudio.app.tospace.domain.infrastructure.notification.center.AllLaunchesNotificationCenter
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class AllLaunchesNotificationCenterImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val notificationManagerCompat: NotificationManagerCompat,
    fcmServiceAdapter: FCMServiceAdapter
) : AllLaunchesNotificationCenter, DefaultNotificationCenter(notificationManagerCompat) {

    companion object {
        private const val ALL_LAUNCHES_NOTIFICATION_ID_PAYLOAD = "all_launches_notification_id"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override val channel: NotificationChannel = NotificationChannel(
        NotificationProperties.ALL_LAUNCHES_NOTIFICATION_CHANNEL_ID,
        context.getString(R.string.notification_all_launches_channel_name),
        NotificationManager.IMPORTANCE_DEFAULT
    )

    private val scope = MainScope()

    override val start: Flow<Unit> by lazy {
        fcmServiceAdapter
            .onMessageReceived
            .map {
                val notificationId = it.data[ALL_LAUNCHES_NOTIFICATION_ID_PAYLOAD]
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
            .shareIn(scope, SharingStarted.WhileSubscribed())
    }

    private fun createNotification(title: String, body: String): Notification = NotificationCompat
        .Builder(context, NotificationProperties.ALL_LAUNCHES_NOTIFICATION_CHANNEL_ID)
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
        notificationManagerCompat.notify(id, NotificationProperties.ALL_LAUNCHES_NOTIFICATION_ID, notification)
    }

    init {
        registerNotificationChannel()
    }
}