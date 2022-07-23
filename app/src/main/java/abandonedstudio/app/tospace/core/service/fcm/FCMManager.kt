package abandonedstudio.app.tospace.core.service.fcm

import abandonedstudio.app.tospace.core.presentation.notification.manager.PushNotificationSettingsManager
import abandonedstudio.app.tospace.core.presentation.notification.manager.Topic
import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject

class FCMManager @Inject constructor(private val firebaseMessaging: FirebaseMessaging) : PushNotificationSettingsManager {

    override fun subscribeToTopic(topic: Topic) {
        firebaseMessaging.subscribeToTopic(topic.value)
    }

    override fun unsubscribeFromTopic(topic: Topic) {
        firebaseMessaging.unsubscribeFromTopic(topic.value)
    }
}