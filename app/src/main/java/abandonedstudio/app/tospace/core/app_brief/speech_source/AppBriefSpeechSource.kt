package abandonedstudio.app.tospace.core.app_brief.speech_source

interface AppBriefSpeechSource {

    suspend fun speakOut()

    /**
     * optional, by default does nothing
     */
    fun destroy() = run { }
}