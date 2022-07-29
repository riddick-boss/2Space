package abandonedstudio.app.tospace.core.data.local.app_brief_preferences

import kotlinx.coroutines.flow.Flow

interface AppBriefPreferencesSource {

    suspend fun saveLaunchesStatus(enabled: Boolean)

    val launchesStatus: Flow<Boolean?>

    suspend fun saveNewsStatus(enabled: Boolean)

    val newsStatus: Flow<Boolean?>

    suspend fun saveNewsToReadNumber(number: Int)

    val articlesToReadNumber: Int?
}