package abandonedstudio.app.tospace.core.data.remote.weather.ktor

import abandonedstudio.app.tospace.BuildConfig

object Routes {

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    private val APP_ID = "&appid=${BuildConfig.OPEN_WEATHER_MAP_API_KEY}"
    private const val UNITS = "&units=metric"

    private val unitsAndAppId = "${UNITS}${APP_ID}"

    fun getRouteCity(city: String): String {
        return "${BASE_URL}weather?q=${city}${unitsAndAppId}"
    }

    fun getRouteCoordinates(lat: String, lon: String): String {
        return "${BASE_URL}weather?lat=${lat}&lon=${lon}${unitsAndAppId}"
    }
}