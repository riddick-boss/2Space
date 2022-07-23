package abandonedstudio.app.tospace.core.service.app_brief_voice_assistant_service

import android.app.PendingIntent
import android.content.Context
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppBriefServiceManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private val pendingIntentStartRequestCode: Int = "pendingIntentStartRequestCode".hashCode()
        private val pendingIntentStopRequestCode: Int = "pendingIntentStopRequestCode".hashCode()

        private var _isRunning = false
        val isRunning: Boolean get() = _isRunning
    }

    fun startService() {
        if (!isRunning) {
            ContextCompat.startForegroundService(context, AppBriefVoiceAssistantService.intentStart(context))
        }
    }

    fun stopService() {
        if (isRunning) {
            ContextCompat.startForegroundService(context, AppBriefVoiceAssistantService.intentStop(context))
        }
    }

    val intentStart: PendingIntent = PendingIntent.getService(context, pendingIntentStartRequestCode, AppBriefVoiceAssistantService.intentStart(context), PendingIntent.FLAG_IMMUTABLE)

    val intentStop: PendingIntent = PendingIntent.getService(context, pendingIntentStopRequestCode, AppBriefVoiceAssistantService.intentStop(context), PendingIntent.FLAG_IMMUTABLE)

    fun onServiceStart() {
        _isRunning = true
    }

    fun onServiceStop() {
        _isRunning = false
    }
}