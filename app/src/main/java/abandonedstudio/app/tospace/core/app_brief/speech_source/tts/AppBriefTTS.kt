package abandonedstudio.app.tospace.core.app_brief.speech_source.tts

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.domain.infrastructure.extension.showToast
import abandonedstudio.app.tospace.core.app_brief.speech_source.AppBriefSpeechSource
import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class AppBriefTTS @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dataSource: DataSource
) : AppBriefSpeechSource, TextToSpeech.OnInitListener {

    private val scope = MainScope()

    private val tts = TextToSpeech(context, this).apply {
        setSpeechRate(0.9f)
    }

    private val isInitialized = MutableStateFlow(false)

    private val speakingDone = MutableSharedFlow<Unit>(replay = 1) // replay in case speaking finished before any subscriber attached

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                context.showToast(R.string.app_brief_tts_language_not_supported)
                return
            }

            tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(p0: String?) {
                    // nothing to do
                }

                override fun onDone(p0: String?) {
                    scope.launch {
                        speakingDone.emit(Unit)
                    }
                }

                @Deprecated("Deprecated in Java")
                override fun onError(p0: String?) {
                    // nothing to do
                }
            })

            isInitialized.value = true
        }

        if (status != TextToSpeech.SUCCESS) {
            context.showToast(R.string.app_brief_tts_init_failed)
        }
    }

    override suspend fun speakOut() {
        isInitialized.filter { it }.first() // await for initialization
        try {
            val toSpeak = dataSource.getContentToSpeak()
            tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED)
        } catch (e: Exception) {
            context.showToast(R.string.app_brief_tts_reading_failed)
            speakingDone.emit(Unit)
        }
        return speakingDone.first() // await for speaking completion
    }

    override fun destroy() {
        tts.stop()
        tts.shutdown()
    }
}