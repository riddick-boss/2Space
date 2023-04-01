package abandonedstudio.app.tospace.core.app_brief.speech_source.tts

import com.google.common.truth.Truth
import org.junit.Test

class ArticleTest {

    @Test
    fun `when description is null, tts returns title only`() {
        val title = "title"
        val description: String? = null
        val article = Article(title, description)
        Truth.assertThat(article.tts).isEqualTo("$title.")
    }

    @Test
    fun `when description is not null, tts returns title and description`() {
        val title = "title"
        val description = "description"
        val article = Article(title, description)
        Truth.assertThat(article.tts).contains(title)
        Truth.assertThat(article.tts).contains(description)
    }
}