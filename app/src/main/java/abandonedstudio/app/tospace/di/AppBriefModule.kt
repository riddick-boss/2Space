package abandonedstudio.app.tospace.di

import abandonedstudio.app.tospace.core.app_brief.AppBriefServiceManager
import abandonedstudio.app.tospace.core.app_brief.speech_source.AppBriefSpeechSource
import abandonedstudio.app.tospace.core.app_brief.speech_source.tts.AppBriefTTS
import abandonedstudio.app.tospace.domain.infrastructure.app_brief.AppBriefManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ServiceComponent::class, SingletonComponent::class)
abstract class AppBriefModule {

    @Binds
    abstract fun bindAppBriefManager(impl: AppBriefServiceManager): AppBriefManager

    @Binds
    abstract fun bindAppBriefSpeechSource(impl: AppBriefTTS): AppBriefSpeechSource
}