package abandonedstudio.app.tospace.domain.repository

import abandonedstudio.app.tospace.domain.model.weather.Weather

interface WeatherRepository {

    suspend fun getWeatherInCity(city: String): Weather

    suspend fun getWeatherAtCoordinates(lon: String, lat: String): Weather
}