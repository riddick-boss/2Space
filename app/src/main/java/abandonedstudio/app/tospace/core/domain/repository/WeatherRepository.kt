package abandonedstudio.app.tospace.core.domain.repository

import abandonedstudio.app.tospace.core.domain.model.weather.Weather

interface WeatherRepository {

    suspend fun getWeatherInCity(city: String): Weather

    suspend fun getWeatherAtCoordinates(lon: String, lat: String): Weather
}