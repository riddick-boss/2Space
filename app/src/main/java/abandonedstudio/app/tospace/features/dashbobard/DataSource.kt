package abandonedstudio.app.tospace.features.dashbobard

import abandonedstudio.app.tospace.core.domain.model.Weather
import abandonedstudio.app.tospace.core.domain.repository.WeatherRepository
import javax.inject.Inject

class DataSource @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend fun loadCanaveralWeather(): Weather {
        try {
            val response = weatherRepository.getWeatherAtCoordinates(
                lat = "",
                lon = ""
            )
            return response
        } catch (e: Exception) {
            throw e
        }
    }
}