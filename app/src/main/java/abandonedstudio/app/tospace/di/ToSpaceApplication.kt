package abandonedstudio.app.tospace.di

import abandonedstudio.app.tospace.core.presentation.notification.AllLaunchesNotificationCenter
import abandonedstudio.app.tospace.core.presentation.notification.NotificationConstants
import abandonedstudio.app.tospace.core.presentation.notification.manager.NotificationSettingsManager
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
    @Inject lateinit var notificationSettingsManager: NotificationSettingsManager

    private val scope = MainScope()

    companion object {
        lateinit var resources: Resources
    }

    override fun onCreate() {
        super.onCreate()
        ToSpaceApplication.resources = resources

        notificationSettingsManager.subscribeToTopic(Topic(NotificationConstants.APP_UPDATE_TOPIC_VALUE))
        notificationSettingsManager.subscribeToTopic(Topic(NotificationConstants.ALL_LAUNCHES_TOPIC_VALUE))

        allLaunchesNotificationCenter.start.launchIn(scope)
    }
}