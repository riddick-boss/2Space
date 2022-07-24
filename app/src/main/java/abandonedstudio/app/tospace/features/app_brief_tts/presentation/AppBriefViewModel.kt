package abandonedstudio.app.tospace.features.app_brief_tts.presentation

import abandonedstudio.app.tospace.core.service.app_brief_voice_assistant_service.AppBriefServiceManager
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppBriefViewModel @Inject constructor(
    private val appBriefServiceManager: AppBriefServiceManager,
    application: Application
) : AndroidViewModel(application) {

    fun onPlayBriefClicked() {
        appBriefServiceManager.startService()
    }
}