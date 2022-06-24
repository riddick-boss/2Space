package abandonedstudio.app.tospace.features.dashbobard.domain

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.domain.model.launches.DetailedLaunch
import abandonedstudio.app.tospace.core.domain.repository.SpaceXRepository
import abandonedstudio.app.tospace.core.domain.repository.WeatherRepository
import abandonedstudio.app.tospace.core.domain.util.DateFormat
import abandonedstudio.app.tospace.core.domain.util.resources.StringUtil
import abandonedstudio.app.tospace.features.dashbobard.data.FacilityWeather
import abandonedstudio.app.tospace.features.dashbobard.data.SpaceXLaunch
import javax.inject.Inject

class DataSource @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val spaceXRepository: SpaceXRepository
) {
    private val noData = StringUtil.getString(R.string.no_data_info)

    suspend fun loadLastLaunch(): SpaceXLaunch =
        spaceXRepository
            .getLastLaunch()
            .toSpacexLaunch()

    suspend fun loadNextLaunch(): SpaceXLaunch =
        spaceXRepository
            .getNextLaunch()
            .toSpacexLaunch()

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

private fun DetailedLaunch.toSpacexLaunch() =
    SpaceXLaunch(
        missionName = missionName,
        logoImgPath = logoImgPath,
        rocket = rocket,
        flightNumber = flightNumber,
        timeStamp = timeStamp,
        date = timeStamp?.let {
            val dateFormat = DateFormat(it)
            "${dateFormat.dayOfWeekShort}, ${dateFormat.format(DateFormat.Precision.MONTH)}"
        },
        links = links.toSpaceXLinks(),
        details = details,
        launchPad = launchPad,
        payloads = payloads.toSpaceXPayloads()
    )

private fun DetailedLaunch.Links.toSpaceXLinks() = SpaceXLaunch.Links(wikipedia, yt, reddit)

private fun List<DetailedLaunch.Payload>.toSpaceXPayloads() =
    this.map {
        SpaceXLaunch.Payload(
            type = it.type,
            massKg = it.massKg,
            orbit = it.orbit,
            inclination = it.inclination,
            periodMin = it.periodMin,
            customers = it.customers
        )
    }