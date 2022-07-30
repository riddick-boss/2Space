package abandonedstudio.app.tospace.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface AppBriefPreferencesRepository {

    companion object {
        const val DEFAULT_ARTICLES_TO_READ_NUMBER = 5
    }

    suspend fun saveLaunchesStatus(enabled: Boolean)

    val launchesStatus: Flow<Boolean>

    suspend fun saveNewsStatus(enabled: Boolean)

    val newsStatus: Flow<Boolean>

    suspend fun saveNewsToReadNumber(number: Int)

    val articlesToRead: Flow<Int>
}