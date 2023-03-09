package abandonedstudio.app.tospace.core.presentation.notification.manager

interface PushNotificationSettingsManager {

    fun subscribeToTopic(topic: Topic)

    fun unsubscribeFromTopic(topic: Topic)
}

@JvmInline
value class Topic(val value: String) {

    init {
        require(value.isNotBlank()) { throw IllegalArgumentException("Topic cannot be blank!") }
    }
}