package abandonedstudio.app.tospace.core.presentation.notification.manager

interface NotificationSettingsManager {

    fun subscribeToTopic(topic: Topic)

    fun unsubscribeFromTopic(topic: Topic)
}

@JvmInline
value class Topic(val value: String) {

    init {
        require(value.isNotBlank()) { throw IllegalArgumentException("FCM topic cannot be blank!") }
    }
}