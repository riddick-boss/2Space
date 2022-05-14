package abandonedstudio.app.tospace.core.data.remote.weather.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class OpenWeatherMapResponse(
    @SerialName("base")
    val base: String? = null,
    @SerialName("clouds")
    val clouds: Clouds? = null,
    @SerialName("cod")
    val cod: Int? = null,
    @SerialName("coord")
    val coord: Coord? = null,
    @SerialName("dt")
    val dt: Int? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("main")
    val main: Main? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("sys")
    val sys: Sys? = null,
    @SerialName("timezone")
    val timezone: Int? = null,
    @SerialName("visibility")
    val visibility: Int? = null,
    @SerialName("weather")
    val weather: List<Weather?>? = null,
    @SerialName("wind")
    val wind: Wind? = null
) {
    @Keep
    @Serializable
    data class Clouds(
        @SerialName("all")
        val all: Int? = null
    )

    @Keep
    @Serializable
    data class Coord(
        @SerialName("lat")
        val lat: Double? = null,
        @SerialName("lon")
        val lon: Double? = null
    )

    @Keep
    @Serializable
    data class Main(
        @SerialName("feels_like")
        val feelsLike: Double? = null,
        @SerialName("humidity")
        val humidity: Int? = null,
        @SerialName("pressure")
        val pressure: Int? = null,
        @SerialName("temp")
        val temp: Double? = null,
        @SerialName("temp_max")
        val tempMax: Double? = null,
        @SerialName("temp_min")
        val tempMin: Double? = null
    )

    @Keep
    @Serializable
    data class Sys(
        @SerialName("country")
        val country: String? = null,
        @SerialName("id")
        val id: Int? = null,
        @SerialName("message")
        val message: Double? = null,
        @SerialName("sunrise")
        val sunrise: Int? = null,
        @SerialName("sunset")
        val sunset: Int? = null,
        @SerialName("type")
        val type: Int? = null
    )

    @Keep
    @Serializable
    data class Weather(
        @SerialName("description")
        val description: String? = null,
        @SerialName("icon")
        val icon: String? = null,
        @SerialName("id")
        val id: Int? = null,
        @SerialName("main")
        val main: String? = null
    )

    @Keep
    @Serializable
    data class Wind(
        @SerialName("deg")
        val deg: Int? = null,
        @SerialName("speed")
        val speed: Double? = null
    )
}