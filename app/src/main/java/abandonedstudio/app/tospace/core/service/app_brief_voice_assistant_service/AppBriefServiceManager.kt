package abandonedstudio.app.tospace.core.service.app_brief_voice_assistant_service

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.domain.util.extension.showToast
import android.app.PendingIntent
import android.content.Context
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.MainScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class AppBriefServiceManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private val pendingIntentStartRequestCode: Int = "pendingIntentStartRequestCode".hashCode()
        private val pendingIntentStopRequestCode: Int = "pendingIntentStopRequestCode".hashCode()
    }

    val scope = MainScope()

    fun startService() {
        try {
            ContextCompat.startForegroundService(context, AppBriefVoiceAssistantService.intentStart(context))
        } catch (e: Exception) {
            context.showToast(R.string.app_brief_voice_service_service_start_error_message)
        }
    }

    fun stopService() {
        try {
            ContextCompat.startForegroundService(context, AppBriefVoiceAssistantService.intentStop(context))
        } catch (e: Exception) {
            // nothing to do
        }
    }

    val intentStart: PendingIntent = PendingIntent.getService(context, pendingIntentStartRequestCode, AppBriefVoiceAssistantService.intentStart(context), PendingIntent.FLAG_IMMUTABLE)

    val intentStop: PendingIntent = PendingIntent.getService(context, pendingIntentStopRequestCode, AppBriefVoiceAssistantService.intentStop(context), PendingIntent.FLAG_IMMUTABLE)
}