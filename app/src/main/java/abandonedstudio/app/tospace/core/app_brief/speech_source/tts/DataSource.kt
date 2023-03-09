package abandonedstudio.app.tospace.core.app_brief.speech_source.tts

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.domain.repository.AppBriefPreferencesRepository
import abandonedstudio.app.tospace.domain.repository.LaunchesRepository
import abandonedstudio.app.tospace.domain.repository.NewsRepository
import abandonedstudio.app.tospace.ToSpaceApplication
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DataSource @Inject constructor(
    private val newsRepository: NewsRepository,
    private val launchesRepository: LaunchesRepository,
    private val appBriefPreferencesRepository: AppBriefPreferencesRepository
) {
    companion object {
        private const val READING_SHORT_BREAK = " ... "
    }

    suspend fun getContentToSpeak(): String {
        val builder = StringBuilder()
        val shouldReadLaunches = appBriefPreferencesRepository.areLaunchesEnabled.first()
        val shouldReadArticles = appBriefPreferencesRepository.areNewsEnabled.first()

        if (shouldReadLaunches) {
            builder.append(convertLaunchToTTS())
        }

        if (shouldReadArticles) {
            builder.append(READING_SHORT_BREAK) // add pause when reading

            val articlesNumber = appBriefPreferencesRepository.articlesToRead.first()
            builder.append(convertArticlesToTTS(number = articlesNumber))
        }
        return builder.toString()
    }

    private suspend fun loadArticles(number: Int): List<Article>? =
        try {
            newsRepository.loadArticles(number).filter { it.title != null }.map {
                Article(it.title!!, it.summary)
            }
        } catch (e: Exception) {
            null
        }

    private suspend fun convertArticlesToTTS(number: Int): String {
        val articlesTTSes = loadArticles(number)?.map { it.tts }
        return articlesTTSes?.joinToString(
            prefix = ToSpaceApplication.resources.getString(R.string.app_brief_tts_articles_prefix),
            postfix = ToSpaceApplication.resources.getString(R.string.app_brief_tts_articles_postfix),
            separator = ToSpaceApplication.resources.getString(R.string.app_brief_tts_articles_separator)
        ) ?: ToSpaceApplication.resources.getString(R.string.app_brief_tts_loading_articles_failed)
    }

    private suspend fun loadUpcomingLaunch(): Launch? =
        try {
            launchesRepository.loadUpcomingLaunch().let {
                Launch(name = it.name!!, description = it.description, timeStamp = it.timeStamp, timeStampMillis = it.timeStampMillis)
            }
        } catch (e: Exception) {
            null
        }

    private suspend fun convertLaunchToTTS(): String =
        loadUpcomingLaunch()?.tts ?: ToSpaceApplication.resources.getString(R.string.app_brief_tts_loading_next_launch_failed)
}