package abandonedstudio.app.tospace.core.data.remote.weather

import abandonedstudio.app.tospace.core.data.remote.weather.dto.OpenWeatherMapResponse

interface WeatherRemoteApi {

    suspend fun getWeatherInCity(city: String): OpenWeatherMapResponse

    suspend fun getWeatherAtCoordinates(lon: String, lat: String): OpenWeatherMapResponse
}