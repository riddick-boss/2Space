package abandonedstudio.app.tospace.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface AppBriefPreferencesRepository {

    suspend fun saveLaunchesStatus(enabled: Boolean)

    val launchesStatus: Flow<Boolean?>

    suspend fun saveNewsStatus(enabled: Boolean)

    val newsStatus: Flow<Boolean?>

    suspend fun saveNewsToReadNumber(number: Int)

    val articlesToRead: Int?
}