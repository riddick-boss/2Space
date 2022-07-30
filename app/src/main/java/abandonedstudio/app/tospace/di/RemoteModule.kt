package abandonedstudio.app.tospace.di

import abandonedstudio.app.tospace.core.data.remote.launches.LaunchesRemoteApi
import abandonedstudio.app.tospace.core.data.remote.launches.ktor.KtorLaunchesRemoteApi
import abandonedstudio.app.tospace.core.data.remote.news.articles.ArticlesRemoteApi
import abandonedstudio.app.tospace.core.data.remote.news.articles.ktor.KtorArticlesRemoteApi
import abandonedstudio.app.tospace.core.data.remote.news.events.EventsRemoteApi
import abandonedstudio.app.tospace.core.data.remote.news.events.ktor.KtorEventsRemoteApi
import abandonedstudio.app.tospace.core.data.remote.spacex.SpaceXRemoteApi
import abandonedstudio.app.tospace.core.data.remote.spacex.ktor.KtorSpaceXRemoteApi
import abandonedstudio.app.tospace.core.data.remote.weather.WeatherRemoteApi
import abandonedstudio.app.tospace.core.data.remote.weather.ktor.KtorWeatherRemoteApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*

//TODO: change to @Binds

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    fun provideWeatherRemoteApi(httpClient: HttpClient): WeatherRemoteApi =
        KtorWeatherRemoteApi(httpClient)

    @Provides
    fun provideSpaceXRemoteApi(httpClient: HttpClient): SpaceXRemoteApi =
        KtorSpaceXRemoteApi(httpClient)

    @Provides
    fun provideEventsRemoteApi(httpClient: HttpClient): EventsRemoteApi =
        KtorEventsRemoteApi(httpClient)

    @Provides
    fun provideArticlesRemoteApi(httpClient: HttpClient): ArticlesRemoteApi =
        KtorArticlesRemoteApi(httpClient)

    @Provides
    fun provideLaunchesRemoteApi(httpClient: HttpClient): LaunchesRemoteApi =
        KtorLaunchesRemoteApi(httpClient)
}