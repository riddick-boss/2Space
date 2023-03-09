package abandonedstudio.app.tospace.domain.infrastructure.notification.push

interface PushNotificationSettingsManager {

    /**
     * subscribes to all available topics
     */
    fun subscribeToTopics()

    fun subscribeToTopic(topic: Topic)

    fun unsubscribeFromTopic(topic: Topic)
}

@JvmInline
value class Topic(val value: String) {

    init {
        require(value.isNotBlank()) { throw IllegalArgumentException("Topic cannot be blank!") }
    }
}