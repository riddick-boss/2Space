package abandonedstudio.app.tospace.core.data.remote.weather.ktor

import abandonedstudio.app.tospace.BuildConfig

object Routes {

    private const val APP_ID = "&appid=${BuildConfig.OPEN_WEATHER_MAP_API_KEY}"
    private const val UNITS = "&units=metric"

    private const val unitsAndAppId = "${UNITS}${APP_ID}"

    fun getRouteCity(city: String): String {
        return "${BuildConfig.OPEN_WEATHER_URL}weather?q=${city}${unitsAndAppId}"
    }

    fun getRouteCoordinates(lat: String, lon: String): String {
        return "${BuildConfig.OPEN_WEATHER_URL}weather?lat=${lat}&lon=${lon}${unitsAndAppId}"
    }
}