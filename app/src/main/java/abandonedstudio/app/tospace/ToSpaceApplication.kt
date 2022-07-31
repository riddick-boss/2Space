package abandonedstudio.app.tospace

import abandonedstudio.app.tospace.core.presentation.notification.AllLaunchesNotificationCenter
import abandonedstudio.app.tospace.core.presentation.notification.NotificationConstants
import abandonedstudio.app.tospace.core.presentation.notification.manager.PushNotificationSettingsManager
import abandonedstudio.app.tospace.core.presentation.notification.manager.Topic
import android.app.Application
import android.content.res.Resources
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltAndroidApp
class ToSpaceApplication : Application() {

    @Inject lateinit var allLaunchesNotificationCenter: AllLaunchesNotificationCenter
    @Inject lateinit var pushNotificationSettingsManager: PushNotificationSettingsManager

    private val scope = MainScope()

    companion object {
        lateinit var resources: Resources
    }

    override fun onCreate() {
        super.onCreate()
        Companion.resources = resources

        pushNotificationSettingsManager.subscribeToTopic(Topic(NotificationConstants.APP_UPDATE_TOPIC_VALUE))
        pushNotificationSettingsManager.subscribeToTopic(Topic(NotificationConstants.ALL_LAUNCHES_TOPIC_VALUE))
        if (BuildConfig.DEBUG) {
            pushNotificationSettingsManager.subscribeToTopic(Topic(NotificationConstants.DEBUG_TOPIC_VALUE))
        }

        allLaunchesNotificationCenter.start.launchIn(scope)
    }
}