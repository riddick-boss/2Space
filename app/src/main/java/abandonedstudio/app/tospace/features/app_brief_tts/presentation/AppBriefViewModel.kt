package abandonedstudio.app.tospace.features.app_brief_tts.presentation

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.domain.repository.AppBriefPreferencesRepository
import abandonedstudio.app.tospace.core.domain.repository.AppBriefPreferencesRepository.Companion.DEFAULT_ARTICLES_TO_READ_NUMBER
import abandonedstudio.app.tospace.core.domain.util.extension.showToast
import abandonedstudio.app.tospace.core.service.app_brief_voice_assistant_service.AppBriefServiceManager
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppBriefViewModel @Inject constructor(
    private val appBriefPreferencesRepository: AppBriefPreferencesRepository,
    private val appBriefServiceManager: AppBriefServiceManager,
    application: Application
) : AndroidViewModel(application) {

    val launchesStatus: StateFlow<Boolean> by lazy {
        appBriefPreferencesRepository
            .launchesStatus
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
            .newsStatus
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

    fun onPlayBriefClicked() {
        appBriefServiceManager.startService()
    }
}