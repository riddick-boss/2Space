package abandonedstudio.app.tospace.core.service.fcm

import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FCMServiceAdapter @Inject constructor() {

    private val scope = MainScope()

    private val _onMessageReceivedFlow = MutableSharedFlow<RemoteMessage>()
    val onMessageReceived: Flow<RemoteMessage> = _onMessageReceivedFlow.asSharedFlow()

    fun onMessageReceived(message: RemoteMessage) {
        scope.launch {
            _onMessageReceivedFlow.emit(message)
        }
    }
}