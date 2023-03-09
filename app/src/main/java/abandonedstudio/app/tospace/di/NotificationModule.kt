package abandonedstudio.app.tospace.di

import abandonedstudio.app.tospace.core.notification.center.AllLaunchesNotificationCenterImpl
import abandonedstudio.app.tospace.domain.infrastructure.notification.push.PushNotificationSettingsManager
import abandonedstudio.app.tospace.core.notification.push.manager.FCMManager
import abandonedstudio.app.tospace.domain.infrastructure.notification.center.AllLaunchesNotificationCenter
import android.content.Context
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessaging
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NotificationModule {

    @Binds
    @Singleton
    abstract fun bindNotificationSettingsManager(impl: FCMManager): PushNotificationSettingsManager

    @Binds
    @Singleton
    abstract fun bindAllLaunchesNotificationCenter(impl: AllLaunchesNotificationCenterImpl): AllLaunchesNotificationCenter

    companion object {
        @Provides
        @Singleton
        fun provideNotificationManagerCompat(@ApplicationContext context: Context): NotificationManagerCompat = NotificationManagerCompat.from(context)

        @Provides
        @Singleton
        fun provideFirebaseMessaging(): FirebaseMessaging = FirebaseMessaging.getInstance()
    }
}