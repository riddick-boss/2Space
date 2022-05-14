package abandonedstudio.app.tospace.core.data.remote.weather.ktor

import abandonedstudio.app.tospace.core.data.remote.weather.WeatherRemoteApi
import abandonedstudio.app.tospace.core.data.remote.weather.dto.OpenWeatherMapResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import javax.inject.Inject

class KtorWeatherRemoteApi @Inject constructor(
    private val client: HttpClient
) : WeatherRemoteApi {
    override suspend fun getWeatherInCity(city: String): OpenWeatherMapResponse =
        client.get(
            Routes.getRouteCity(city)
        ).body()

    override suspend fun getWeatherAtCoordinates(lon: String, lat: String): OpenWeatherMapResponse =
        client.get(
            Routes.getRouteCoordinates(lat, lon)
        ).body()
}