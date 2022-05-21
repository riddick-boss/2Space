package abandonedstudio.app.tospace.di

import abandonedstudio.app.tospace.core.data.remote.spacex.SpaceXRemoteApi
import abandonedstudio.app.tospace.core.data.remote.spacex.ktor.KtorSpaceXRemoteApi
import abandonedstudio.app.tospace.core.data.remote.weather.WeatherRemoteApi
import abandonedstudio.app.tospace.core.data.remote.weather.ktor.KtorWeatherRemoteApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    fun provideWeatherRemoteApi(httpClient: HttpClient): WeatherRemoteApi =
        KtorWeatherRemoteApi(httpClient)

    @Provides
    fun provideSpaceXRemoteApi(httpClient: HttpClient) : SpaceXRemoteApi =
        KtorSpaceXRemoteApi(httpClient)
}