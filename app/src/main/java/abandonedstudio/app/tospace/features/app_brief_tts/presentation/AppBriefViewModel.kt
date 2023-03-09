package abandonedstudio.app.tospace.features.app_brief_tts.presentation

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.domain.infrastructure.app_brief.AppBriefManager
import abandonedstudio.app.tospace.domain.infrastructure.extension.showToast
import abandonedstudio.app.tospace.domain.repository.AppBriefPreferencesRepository
import abandonedstudio.app.tospace.domain.repository.AppBriefPreferencesRepository.Companion.DEFAULT_ARTICLES_TO_READ_NUMBER
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppBriefViewModel @Inject constructor(
    private val appBriefPreferencesRepository: AppBriefPreferencesRepository,
    private val appBriefManager: AppBriefManager,
    application: Application
) : AndroidViewModel(application) {

    val articlesToReadRange = 1..20

    val launchesStatus: StateFlow<Boolean> by lazy {
        appBriefPreferencesRepository
            .areLaunchesEnabled
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)
    }

    fun onLaunchesStatusCheckedChange(enabled: Boolean) {
        try {
            viewModelScope.launch {
                appBriefPreferencesRepository.saveLaunchesStatus(enabled)
            }
        } catch (e: Exception) {
            showToast(R.string.app_brief_screen_setting_save_error_message)
        }
    }

    val articlesStatus: StateFlow<Boolean> by lazy {
        appBriefPreferencesRepository
            .areNewsEnabled
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)
    }

    fun onArticlesStatusCheckedChange(enabled: Boolean) {
        try {
            viewModelScope.launch {
                appBriefPreferencesRepository.saveNewsStatus(enabled)
            }
        } catch (e: Exception) {
            showToast(R.string.app_brief_screen_setting_save_error_message)
        }
    }

    val articlesToReadNumber: StateFlow<Int> by lazy {
        appBriefPreferencesRepository
            .articlesToRead
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), DEFAULT_ARTICLES_TO_READ_NUMBER)
    }

    fun onArticlesToReadNumberChange(number: Int) {
        try {
            viewModelScope.launch {
                appBriefPreferencesRepository.saveNewsToReadNumber(number)
            }
        } catch (e: Exception) {
            showToast(R.string.app_brief_screen_setting_save_error_message)
        }
    }

    val playAppBriefButtonVisible: StateFlow<Boolean> by lazy {
        combine(launchesStatus, articlesStatus) { t1, t2 -> t1 || t2 }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)
    }

    fun onPlayBriefClicked() {
        appBriefManager.start()
    }
}