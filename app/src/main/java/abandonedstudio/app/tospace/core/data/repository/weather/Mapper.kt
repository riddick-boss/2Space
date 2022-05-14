package abandonedstudio.app.tospace.core.data.repository.weather

import abandonedstudio.app.tospace.core.data.remote.weather.dto.OpenWeatherMapResponse
import abandonedstudio.app.tospace.core.domain.model.Weather

fun OpenWeatherMapResponse.toWeather() =
    Weather(
        temperature = this.main?.temp,
        summary = this.weather?.first()?.main,
        wind = this.wind?.speed
    )