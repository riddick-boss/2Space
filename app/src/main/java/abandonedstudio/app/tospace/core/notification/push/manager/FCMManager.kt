package abandonedstudio.app.tospace.core.notification.push.manager

import abandonedstudio.app.tospace.BuildConfig
import abandonedstudio.app.tospace.domain.infrastructure.notification.NotificationProperties
import abandonedstudio.app.tospace.domain.infrastructure.notification.push.PushNotificationSettingsManager
import abandonedstudio.app.tospace.domain.infrastructure.notification.push.Topic
import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject

class FCMManager @Inject constructor(private val firebaseMessaging: FirebaseMessaging) : PushNotificationSettingsManager {

    override fun subscribeToTopics() {
        subscribeToTopic(Topic(NotificationProperties.APP_UPDATE_TOPIC_VALUE))
        subscribeToTopic(Topic(NotificationProperties.ALL_LAUNCHES_TOPIC_VALUE))
        if (BuildConfig.DEBUG) {
            subscribeToTopic(Topic(NotificationProperties.DEBUG_TOPIC_VALUE))
        }
    }

    override fun subscribeToTopic(topic: Topic) {
        firebaseMessaging.subscribeToTopic(topic.value)
    }

    override fun unsubscribeFromTopic(topic: Topic) {
        firebaseMessaging.unsubscribeFromTopic(topic.value)
    }
}