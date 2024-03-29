package abandonedstudio.app.tospace.domain.infrastructure.notification

import abandonedstudio.app.tospace.BuildConfig

object NotificationProperties {
    const val ALL_LAUNCHES_NOTIFICATION_CHANNEL_ID = "ALL_LAUNCHES_NOTIFICATION_CHANNEL_ID"
    const val APP_BRIEF_VOICE_ASSISTANT_SERVICE_NOTIFICATION_CHANNEL_ID = "APP_BRIEF_VOICE_ASSISTANT_SERVICE_NOTIFICATION_CHANNEL_ID"

    const val ALL_LAUNCHES_NOTIFICATION_ID = 1
    const val APP_BRIEF_VOICE_ASSISTANT_SERVICE_NOTIFICATION_ID = 2

    const val ALL_LAUNCHES_TOPIC_VALUE = BuildConfig.ALL_LAUNCHES_TOPIC_VALUE
    const val APP_UPDATE_TOPIC_VALUE = BuildConfig.APP_UPDATE_TOPIC_VALUE
    const val DEBUG_TOPIC_VALUE = BuildConfig.DEBUG_TOPIC_VALUE
}