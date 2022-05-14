package abandonedstudio.app.tospace.features.dashbobard

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.domain.repository.WeatherRepository
import abandonedstudio.app.tospace.core.domain.util.resources.StringUtil
import javax.inject.Inject

class DataSource @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    private suspend fun loadWeather(coordinates: Coordinates): FacilityWeather {
        try {
            val response = weatherRepository.getWeatherAtCoordinates(
                lat = coordinates.lat,
                lon = coordinates.lon
            )
            val wind = response.wind?.let {
                StringUtil.getString(R.string.dashboard_temperature_content, it)
            } ?: StringUtil.getString(R.string.dashboard_no_data)

            val summary = response.summary ?: StringUtil.getString(R.string.dashboard_no_data)

            val temperature = response.temperature?.let {
                StringUtil.getString(R.string.dashboard_temperature_content, it)
            } ?: StringUtil.getString(R.string.dashboard_no_data)

            return FacilityWeather(
                wind = wind,
                summary = summary,
                temperature = temperature
            )
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun loadCanaveralWeather(): FacilityWeather = loadWeather(Coordinates.CANAVERAL)

    suspend fun loadStarbaseWeather(): FacilityWeather = loadWeather(Coordinates.STARBASE)

    suspend fun loadVandenbergWeather(): FacilityWeather = loadWeather(Coordinates.VANDENBERG)
}

private sealed class Coordinates(val lat: String, val lon: String) {
    object CANAVERAL: Coordinates(lat = "-80.5906", lon = "28.5830")
    object STARBASE: Coordinates(lat = "-97.1558", lon = "25.9968")
    object VANDENBERG: Coordinates(lat = "-120.6106", lon = "34.6321")
}