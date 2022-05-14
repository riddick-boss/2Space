package abandonedstudio.app.tospace.core.data.remote.weather.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class OpenWeatherMapResponse(
    @SerialName("base")
    val base: String,
    @SerialName("clouds")
    val clouds: Clouds,
    @SerialName("cod")
    val cod: Int,
    @SerialName("coord")
    val coord: Coord,
    @SerialName("dt")
    val dt: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("main")
    val main: Main,
    @SerialName("name")
    val name: String,
    @SerialName("sys")
    val sys: Sys,
    @SerialName("timezone")
    val timezone: Int,
    @SerialName("visibility")
    val visibility: Int,
    @SerialName("weather")
    val weather: List<Weather>,
    @SerialName("wind")
    val wind: Wind
) {
    @Keep
    @Serializable
    data class Clouds(
        @SerialName("all")
        val all: Int
    )

    @Keep
    @Serializable
    data class Coord(
        @SerialName("lat")
        val lat: Double,
        @SerialName("lon")
        val lon: Double
    )

    @Keep
    @Serializable
    data class Main(
        @SerialName("feels_like")
        val feelsLike: Double,
        @SerialName("humidity")
        val humidity: Int,
        @SerialName("pressure")
        val pressure: Int,
        @SerialName("temp")
        val temp: Double,
        @SerialName("temp_max")
        val tempMax: Double,
        @SerialName("temp_min")
        val tempMin: Double
    )

    @Keep
    @Serializable
    data class Sys(
        @SerialName("country")
        val country: String,
        @SerialName("id")
        val id: Int,
        @SerialName("message")
        val message: Double,
        @SerialName("sunrise")
        val sunrise: Int,
        @SerialName("sunset")
        val sunset: Int,
        @SerialName("type")
        val type: Int
    )

    @Keep
    @Serializable
    data class Weather(
        @SerialName("description")
        val description: String,
        @SerialName("icon")
        val icon: String,
        @SerialName("id")
        val id: Int,
        @SerialName("main")
        val main: String
    )

    @Keep
    @Serializable
    data class Wind(
        @SerialName("deg")
        val deg: Int,
        @SerialName("speed")
        val speed: Double
    )
}