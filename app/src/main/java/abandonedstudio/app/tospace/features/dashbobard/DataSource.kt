package abandonedstudio.app.tospace.features.dashbobard

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.domain.repository.WeatherRepository
import abandonedstudio.app.tospace.core.domain.util.resources.StringUtil
import javax.inject.Inject

class DataSource @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend fun loadCanaveralWeather(): FacilityWeather {
        try {
            val response = weatherRepository.getWeatherAtCoordinates(
                lat = "",
                lon = ""
            )
            val wind = response.wind?.let {
                StringUtil.getString(
                    R.string.dashboard_temperature_content,
                    it
                )
            } ?: StringUtil.getString(R.string.dashboard_no_data)

            val summary = response.summary ?: StringUtil.getString(R.string.dashboard_no_data)

            val temperature = response.temperature?.let {
                StringUtil.getString(
                    R.string.dashboard_temperature_content,
                    it
                )
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
}