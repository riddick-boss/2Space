package abandonedstudio.app.tospace.core.app_brief.service

import abandonedstudio.app.tospace.core.notification.center.AppBriefVoiceAssistantNotificationCenter
import abandonedstudio.app.tospace.core.app_brief.speech_source.AppBriefSpeechSource
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AppBriefVoiceAssistantService : Service() {

    companion object {
        private const val ACTION_START = "AppBriefVoiceAssistantServiceStart"
        private const val ACTION_STOP = "AppBriefVoiceAssistantServiceStop"
        fun intentStart(context: Context): Intent = Intent(context, AppBriefVoiceAssistantService::class.java).apply {
            action = ACTION_START
        }
        fun intentStop(context: Context): Intent = Intent(context, AppBriefVoiceAssistantService::class.java).apply {
            action = ACTION_STOP
        }
    }

    @Inject lateinit var notificationCenter: AppBriefVoiceAssistantNotificationCenter
    @Inject lateinit var appBriefSpeechSource: AppBriefSpeechSource

    override fun onBind(p0: Intent?): IBinder? = null

    private val scope = MainScope()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action != null && intent.action.equals(ACTION_STOP)) {
            stopService()
            return START_NOT_STICKY
        }

        startForeground()
        scope.launch {
            appBriefSpeechSource.speakOut()
            stopService()
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        scope.cancel()
        appBriefSpeechSource.destroy()
        super.onDestroy()
    }

    private fun stopService() {
        stopForeground(true)
        stopSelf()
    }

    private fun startForeground() {
        startForeground(notificationCenter.notificationId, notificationCenter.notification)
    }
}