package abandonedstudio.app.tospace.core.data.repository.weather

import abandonedstudio.app.tospace.core.data.remote.weather.WeatherRemoteApi
import abandonedstudio.app.tospace.core.domain.model.Weather
import abandonedstudio.app.tospace.core.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteApi: WeatherRemoteApi
) : WeatherRepository {

    override suspend fun getWeatherInCity(city: String): Weather =
        remoteApi.getWeatherInCity(city).toWeather()

    override suspend fun getWeatherAtCoordinates(lon: String, lat: String): Weather =
        remoteApi.getWeatherAtCoordinates(lon, lat).toWeather()
}