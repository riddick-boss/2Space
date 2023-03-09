package abandonedstudio.app.tospace.core.notification.center

import android.app.NotificationChannel
import android.os.Build
import androidx.core.app.NotificationManagerCompat

abstract class DefaultNotificationCenter(
    private val notificationManagerCompat: NotificationManagerCompat
) {

    protected abstract val channel: NotificationChannel

    protected fun registerNotificationChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return

        notificationManagerCompat.createNotificationChannel(channel)
    }
}