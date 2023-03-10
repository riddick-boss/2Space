package abandonedstudio.app.tospace.core.notification.push

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FCMService : FirebaseMessagingService() {

    @Inject lateinit var adapter: FCMServiceAdapter

    override fun onMessageReceived(message: RemoteMessage) {
        adapter.onMessageReceived(message)
    }

    override fun onNewToken(token: String) {
        // nothing to do...
    }
}