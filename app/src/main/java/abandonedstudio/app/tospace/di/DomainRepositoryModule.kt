package abandonedstudio.app.tospace.di

import abandonedstudio.app.tospace.core.data.repository.app_brief_preferences.AppBriefPreferencesRepositoryImpl
import abandonedstudio.app.tospace.core.data.repository.launches.LaunchesRepositoryImpl
import abandonedstudio.app.tospace.core.data.repository.news.NewsRepositoryImpl
import abandonedstudio.app.tospace.core.data.repository.weather.WeatherRepositoryImpl
import abandonedstudio.app.tospace.core.domain.repository.AppBriefPreferencesRepository
import abandonedstudio.app.tospace.core.domain.repository.LaunchesRepository
import abandonedstudio.app.tospace.core.domain.repository.NewsRepository
import abandonedstudio.app.tospace.core.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class, ServiceComponent::class)
abstract class DomainRepositoryModuleViewModelComponent {

    @Binds
    abstract fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

    @Binds
    abstract fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository

    @Binds
    abstract fun bindLaunchesRepository(impl: LaunchesRepositoryImpl): LaunchesRepository

    @Binds
    abstract fun bindAppBriefPreferencesRepository(impl: AppBriefPreferencesRepositoryImpl): AppBriefPreferencesRepository
}