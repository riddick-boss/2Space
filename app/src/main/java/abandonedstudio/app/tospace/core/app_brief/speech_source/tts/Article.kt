package abandonedstudio.app.tospace.core.app_brief.speech_source.tts

data class Article(private val title: String, private val description: String?) {

    val tts = if (description != null) "$title. $description." else "$title."
}