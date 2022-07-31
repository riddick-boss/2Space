package abandonedstudio.app.tospace.core.service.app_brief_voice_assistant_service

interface AppBriefSpeechSource {

    suspend fun speakOut()

    /**
     * optional, by default does nothing
     */
    fun onDestroy() = run { }
}