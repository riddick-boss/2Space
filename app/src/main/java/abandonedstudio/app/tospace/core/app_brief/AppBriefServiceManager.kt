package abandonedstudio.app.tospace.core.app_brief

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.app_brief.service.AppBriefVoiceAssistantService
import abandonedstudio.app.tospace.domain.infrastructure.app_brief.AppBriefManager
import abandonedstudio.app.tospace.domain.infrastructure.extension.showToast
import android.app.PendingIntent
import android.content.Context
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppBriefServiceManager @Inject constructor(
    @ApplicationContext private val context: Context
): AppBriefManager {
    companion object {
        private val pendingIntentStartRequestCode: Int = "pendingIntentStartRequestCode".hashCode()
        private val pendingIntentStopRequestCode: Int = "pendingIntentStopRequestCode".hashCode()
    }

    override fun start() {
        try {
            ContextCompat.startForegroundService(context,
                AppBriefVoiceAssistantService.intentStart(context)
            )
        } catch (e: Exception) {
            context.showToast(R.string.app_brief_voice_service_service_start_error_message)
        }
    }

    override fun stop() {
        try {
            ContextCompat.startForegroundService(context,
                AppBriefVoiceAssistantService.intentStop(context)
            )
        } catch (e: Exception) {
            // nothing to do
        }
    }

    override val intentStart: PendingIntent = PendingIntent.getService(context, pendingIntentStartRequestCode,
        AppBriefVoiceAssistantService.intentStart(context), PendingIntent.FLAG_IMMUTABLE)

    override val intentStop: PendingIntent = PendingIntent.getService(context, pendingIntentStopRequestCode,
        AppBriefVoiceAssistantService.intentStop(context), PendingIntent.FLAG_IMMUTABLE)
}