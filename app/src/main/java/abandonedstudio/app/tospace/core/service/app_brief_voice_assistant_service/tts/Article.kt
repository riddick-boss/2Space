package abandonedstudio.app.tospace.core.service.app_brief_voice_assistant_service.tts

data class Article(private val title: String, private val description: String?) {

    val tts = if (description != null) "$title. $description." else "$title."
}