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
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class, ServiceComponent::class)
abstract class RemoteModule {

    @Binds
    abstract fun bindWeatherRemoteApi(impl: KtorWeatherRemoteApi): WeatherRemoteApi

    @Binds
    abstract fun bindSpaceXRemoteApi(impl: KtorSpaceXRemoteApi): SpaceXRemoteApi

    @Binds
    abstract fun bindEventsRemoteApi(impl: KtorEventsRemoteApi): EventsRemoteApi

    @Binds
    abstract fun bindArticlesRemoteApi(impl: KtorArticlesRemoteApi): ArticlesRemoteApi

    @Binds
    abstract fun bindLaunchesRemoteApi(impl: KtorLaunchesRemoteApi): LaunchesRemoteApi
}