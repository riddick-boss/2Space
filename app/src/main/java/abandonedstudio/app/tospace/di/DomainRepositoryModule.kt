package abandonedstudio.app.tospace.di

import abandonedstudio.app.tospace.core.data.remote.news.articles.ArticlesRemoteApi
import abandonedstudio.app.tospace.core.data.remote.news.events.EventsRemoteApi
import abandonedstudio.app.tospace.core.data.remote.spacex.SpaceXRemoteApi
import abandonedstudio.app.tospace.core.data.remote.weather.WeatherRemoteApi
import abandonedstudio.app.tospace.core.data.repository.news.NewsRepositoryImpl
import abandonedstudio.app.tospace.core.data.repository.spacex.SpaceXRepositoryImpl
import abandonedstudio.app.tospace.core.data.repository.weather.WeatherRepositoryImpl
import abandonedstudio.app.tospace.core.domain.repository.NewsRepository
import abandonedstudio.app.tospace.core.domain.repository.SpaceXRepository
import abandonedstudio.app.tospace.core.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainRepositoryModule {

    @Provides
    fun provideWeatherRepository(remoteApi: WeatherRemoteApi): WeatherRepository =
        WeatherRepositoryImpl(remoteApi)

    @Provides
    fun provideSpaceXRepository(remoteApi: SpaceXRemoteApi) : SpaceXRepository =
        SpaceXRepositoryImpl(remoteApi)

    @Provides
    fun provideNewsRepository(eventsApi: EventsRemoteApi, articlesApi: ArticlesRemoteApi) : NewsRepository =
        NewsRepositoryImpl(eventsApi, articlesApi)
}