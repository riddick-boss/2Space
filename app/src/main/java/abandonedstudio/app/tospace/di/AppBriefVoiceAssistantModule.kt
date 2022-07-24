package abandonedstudio.app.tospace.di

import abandonedstudio.app.tospace.core.service.app_brief_voice_assistant_service.AppBriefSpeechSource
import abandonedstudio.app.tospace.core.service.app_brief_voice_assistant_service.tts.AppBriefTTS
import abandonedstudio.app.tospace.core.service.app_brief_voice_assistant_service.tts.DataSource
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ServiceComponent::class)
object AppBriefVoiceAssistantModule {

    @Provides
    fun provideAppBriefSpeechSource(
        @ApplicationContext context: Context,
        dataSource: DataSource
    ): AppBriefSpeechSource = AppBriefTTS(context, dataSource)
}