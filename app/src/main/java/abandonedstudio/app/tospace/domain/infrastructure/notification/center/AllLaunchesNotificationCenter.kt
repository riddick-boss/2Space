package abandonedstudio.app.tospace.domain.infrastructure.notification.center

import kotlinx.coroutines.flow.Flow

interface AllLaunchesNotificationCenter {

    val start: Flow<Unit>
}