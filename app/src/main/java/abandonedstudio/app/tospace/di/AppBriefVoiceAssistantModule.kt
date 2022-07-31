package abandonedstudio.app.tospace.di

import abandonedstudio.app.tospace.core.service.app_brief_voice_assistant_service.AppBriefSpeechSource
import abandonedstudio.app.tospace.core.service.app_brief_voice_assistant_service.tts.AppBriefTTS
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent

@Module
@InstallIn(ServiceComponent::class)
abstract class AppBriefVoiceAssistantModule {

    @Binds
    abstract fun bindAppBriefSpeechSource(impl: AppBriefTTS): AppBriefSpeechSource
}