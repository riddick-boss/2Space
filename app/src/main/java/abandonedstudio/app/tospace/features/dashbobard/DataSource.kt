package abandonedstudio.app.tospace.features.dashbobard

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.domain.model.Launch
import abandonedstudio.app.tospace.core.domain.repository.SpaceXRepository
import abandonedstudio.app.tospace.core.domain.repository.WeatherRepository
import abandonedstudio.app.tospace.core.domain.util.resources.StringUtil
import javax.inject.Inject

class DataSource @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val spaceXRepository: SpaceXRepository
) {
    private val noData = StringUtil.getString(R.string.dashboard_no_data)

    suspend fun loadLastLaunch(): Launch =
        spaceXRepository.getLastLaunch() //TODO

    suspend fun loadNextLaunch(): Launch =
        spaceXRepository.getNextLaunch() // TODO

    private suspend fun loadWeather(coordinates: Coordinates): FacilityWeather =
        try {
            val response = weatherRepository.getWeatherAtCoordinates(
                lat = coordinates.lat,
                lon = coordinates.lon
            )
            val wind = response.wind?.let {
                StringUtil.getString(R.string.dashboard_wind_content, it)
            } ?: noData

            val summary = response.summary ?: noData

            val temperature = response.temperature?.let {
                StringUtil.getString(R.string.dashboard_temperature_content, it)
            } ?: noData

            FacilityWeather(
                wind = wind,
                summary = summary,
                temperature = temperature
            )
        } catch (e: Exception) {
            FacilityWeather(
                wind = noData,
                summary = noData,
                temperature = noData
            )
        }

    suspend fun loadCanaveralWeather(): FacilityWeather = loadWeather(Coordinates.CANAVERAL)

    suspend fun loadStarbaseWeather(): FacilityWeather = loadWeather(Coordinates.STARBASE)

    suspend fun loadVandenbergWeather(): FacilityWeather = loadWeather(Coordinates.VANDENBERG)
}

private sealed class Coordinates(val lat: String, val lon: String) {
    object CANAVERAL : Coordinates(lat = "28.5830", lon = "-80.5906")
    object STARBASE : Coordinates(lat = "25.9968", lon = "-97.1558")
    object VANDENBERG : Coordinates(lat = "34.6321", lon = "-120.6106")
}