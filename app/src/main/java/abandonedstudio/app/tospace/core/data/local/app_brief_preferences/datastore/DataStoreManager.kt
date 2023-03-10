package abandonedstudio.app.tospace.core.data.local.app_brief_preferences.datastore

import abandonedstudio.app.tospace.core.data.local.app_brief_preferences.AppBriefPreferencesSource
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AppBriefPreferencesSource {

    companion object {
        private const val LAUNCHES_STATE_KEY_NAME = "LAUNCHES_STATE_KEY_NAME"
        private const val NEWS_STATE_KEY_NAME = "NEWS_STATE_KEY_NAME"
        private const val ARTICLES_NUMBER_KEY_NAME = "ARTICLES_NUMBER_KEY_NAME"

        private val LAUNCHES_STATE_KEY = booleanPreferencesKey(LAUNCHES_STATE_KEY_NAME)
        private val NEWS_STATE_KEY = booleanPreferencesKey(NEWS_STATE_KEY_NAME)
        private val ARTICLES_NUMBER_KEY = intPreferencesKey(ARTICLES_NUMBER_KEY_NAME)
    }

    override suspend fun saveLaunchesStatus(enabled: Boolean) {
        dataStore.edit { it[LAUNCHES_STATE_KEY] = enabled }
    }

    override val areLaunchesEnabled: Flow<Boolean?>
        get() = dataStore.data.map { it[LAUNCHES_STATE_KEY] }.distinctUntilChanged()

    override suspend fun saveNewsStatus(enabled: Boolean) {
        dataStore.edit { it[NEWS_STATE_KEY] = enabled }
    }

    override val areNewsEnabled: Flow<Boolean?>
        get() = dataStore.data.map { it[NEWS_STATE_KEY] }.distinctUntilChanged()

    override suspend fun saveNewsToReadNumber(number: Int) {
        dataStore.edit { it[ARTICLES_NUMBER_KEY] = number }
    }

    override val articlesToReadNumber: Flow<Int?>
        get() = dataStore.data.map { it[ARTICLES_NUMBER_KEY] }.distinctUntilChanged()
}