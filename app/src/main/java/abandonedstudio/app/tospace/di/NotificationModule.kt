package abandonedstudio.app.tospace.di

import abandonedstudio.app.tospace.core.presentation.notification.manager.PushNotificationSettingsManager
import abandonedstudio.app.tospace.core.service.fcm.FCMManager
import android.content.Context
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessaging
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    @Provides
    fun provideNotificationManagerCompat(@ApplicationContext context: Context): NotificationManagerCompat = NotificationManagerCompat.from(context)

    @Provides
    fun provideFirebaseMessaging(): FirebaseMessaging = FirebaseMessaging.getInstance()

    @Provides
    fun provideNotificationSettingsManager(firebaseMessaging: FirebaseMessaging): PushNotificationSettingsManager = FCMManager(firebaseMessaging)
}