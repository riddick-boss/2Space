package abandonedstudio.app.tospace

import abandonedstudio.app.tospace.domain.infrastructure.notification.center.AllLaunchesNotificationCenter
import abandonedstudio.app.tospace.domain.infrastructure.notification.push.PushNotificationSettingsManager
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

        pushNotificationSettingsManager.subscribeToTopics()

        allLaunchesNotificationCenter.start.launchIn(scope)
    }
}