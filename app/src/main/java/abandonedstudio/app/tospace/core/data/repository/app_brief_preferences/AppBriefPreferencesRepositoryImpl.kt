package abandonedstudio.app.tospace.core.data.repository.app_brief_preferences

import abandonedstudio.app.tospace.core.data.local.app_brief_preferences.AppBriefPreferencesSource
import abandonedstudio.app.tospace.core.domain.repository.AppBriefPreferencesRepository
import abandonedstudio.app.tospace.core.domain.repository.AppBriefPreferencesRepository.Companion.DEFAULT_ARTICLES_TO_READ_NUMBER
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppBriefPreferencesRepositoryImpl @Inject constructor(
    private val appBriefPreferencesSource: AppBriefPreferencesSource
) : AppBriefPreferencesRepository {

    override suspend fun saveLaunchesStatus(enabled: Boolean) {
        appBriefPreferencesSource.saveLaunchesStatus(enabled)
    }

    override val launchesStatus: Flow<Boolean>
        get() = appBriefPreferencesSource.launchesStatus.map { it ?: false }

    override suspend fun saveNewsStatus(enabled: Boolean) {
        appBriefPreferencesSource.saveNewsStatus(enabled)
    }

    override val newsStatus: Flow<Boolean>
        get() = appBriefPreferencesSource.newsStatus.map { it ?: false }

    override suspend fun saveNewsToReadNumber(number: Int) {
        appBriefPreferencesSource.saveNewsToReadNumber(number)
    }

    override val articlesToRead: Flow<Int>
        get() = appBriefPreferencesSource.articlesToReadNumber.map { it ?: DEFAULT_ARTICLES_TO_READ_NUMBER }
}