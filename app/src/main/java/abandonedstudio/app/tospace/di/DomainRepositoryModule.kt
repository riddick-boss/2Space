package abandonedstudio.app.tospace.di

import abandonedstudio.app.tospace.core.data.remote.launches.LaunchesRemoteApi
import abandonedstudio.app.tospace.core.data.remote.news.articles.ArticlesRemoteApi
import abandonedstudio.app.tospace.core.data.remote.news.events.EventsRemoteApi
import abandonedstudio.app.tospace.core.data.remote.spacex.SpaceXRemoteApi
import abandonedstudio.app.tospace.core.data.remote.weather.WeatherRemoteApi
import abandonedstudio.app.tospace.core.data.repository.app_brief_preferences.AppBriefPreferencesRepositoryImpl
import abandonedstudio.app.tospace.core.data.repository.launches.LaunchesRepositoryImpl
import abandonedstudio.app.tospace.core.data.repository.news.NewsRepositoryImpl
import abandonedstudio.app.tospace.core.data.repository.spacex.SpaceXRepositoryImpl
import abandonedstudio.app.tospace.core.data.repository.weather.WeatherRepositoryImpl
import abandonedstudio.app.tospace.core.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainRepositoryModule {

    @Provides
    fun provideWeatherRepository(remoteApi: WeatherRemoteApi): WeatherRepository =
        WeatherRepositoryImpl(remoteApi)

    @Provides
    fun provideSpaceXRepository(remoteApi: SpaceXRemoteApi): SpaceXRepository =
        SpaceXRepositoryImpl(remoteApi)

    @Provides
    fun provideNewsRepository(eventsApi: EventsRemoteApi, articlesApi: ArticlesRemoteApi): NewsRepository =
        NewsRepositoryImpl(eventsApi, articlesApi)

    @Provides
    fun provideLaunchesRepository(remoteApi: LaunchesRemoteApi): LaunchesRepository =
        LaunchesRepositoryImpl(remoteApi)
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainRepositoryModuleVMComponent {

    @Binds
    abstract fun bindAppBriefPreferencesRepository(impl: AppBriefPreferencesRepositoryImpl): AppBriefPreferencesRepository
}