package abandonedstudio.app.tospace.core.data.remote.weather.dto

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class OpenWeatherMapResponse(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
) {
    @Keep
    @Serializable
    data class Clouds(
        val all: Int
    )

    @Keep
    @Serializable
    data class Coord(
        val lat: Double,
        val lon: Double
    )

    @Keep
    @Serializable
    data class Main(
        val feels_like: Double,
        val humidity: Int,
        val pressure: Int,
        val temp: Double,
        val temp_max: Double,
        val temp_min: Double
    )

    @Keep
    @Serializable
    data class Sys(
        val country: String,
        val id: Int,
        val sunrise: Int,
        val sunset: Int,
        val type: Int
    )

    @Keep
    @Serializable
    data class Weather(
        val description: String,
        val icon: String,
        val id: Int,
        val main: String
    )

    @Keep
    @Serializable
    data class Wind(
        val deg: Int,
        val speed: Double
    )
}