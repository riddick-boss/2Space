package abandonedstudio.app.tospace.di

import abandonedstudio.app.tospace.core.data.local.app_brief_preferences.AppBriefPreferencesSource
import abandonedstudio.app.tospace.core.data.local.app_brief_preferences.datastore.DataStoreManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LocalStoreModule {

    @Binds
    abstract fun bindAppBriefPreferencesSource(impl: DataStoreManager): AppBriefPreferencesSource
}