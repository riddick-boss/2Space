package abandonedstudio.app.tospace.features.app_brief_tts.presentation

import abandonedstudio.app.tospace.core.service.app_brief_voice_assistant_service.AppBriefServiceManager
import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppBriefViewModel @Inject constructor(
    private val appBriefServiceManager: AppBriefServiceManager,
    application: Application
) : AndroidViewModel(application) {

    private val _playBriefButtonVisible = mutableStateOf(true)
    val playBriefButtonVisible: State<Boolean> get() = _playBriefButtonVisible

    fun onPlayBriefClicked() {
        appBriefServiceManager.startService()
        _playBriefButtonVisible.value = false
    }
}