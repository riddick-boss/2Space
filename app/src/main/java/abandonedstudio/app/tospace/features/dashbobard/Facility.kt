package abandonedstudio.app.tospace.features.dashbobard

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.domain.util.resources.StringUtil
import abandonedstudio.app.tospace.core.presentation.component.TitledText
import abandonedstudio.app.tospace.core.presentation.util.contentDescription
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

enum class Facility(
    @StringRes val locationResId: Int,
    @StringRes val regionResId: Int,
    @StringRes val launchpadNameResId: Int,
    @DrawableRes val imageResId: Int
) {

    CAPE_CANAVERAL(
        locationResId = R.string.main_drawer_dashboard_title,
        regionResId = R.string.main_drawer_dashboard_title,
        launchpadNameResId = R.string.main_drawer_dashboard_title,
        imageResId = R.drawable.ic_launcher_foreground //TODO: image
    ) {
        @Composable
        override fun Content(weather: FacilityWeather) {
            FacilityCard(
                locationResId = locationResId,
                regionResId = regionResId,
                launchpadNameResId = launchpadNameResId,
                imageResId = imageResId,
                weather = weather
            )
        }
    };

    @Composable
    abstract fun Content(weather: FacilityWeather)
}

@Composable
private fun FacilityCard(
    @StringRes locationResId: Int,
    @StringRes regionResId: Int,
    @StringRes launchpadNameResId: Int,
    @DrawableRes imageResId: Int,
    weather: FacilityWeather
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = contentDescription(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FacilityLocation(
                        locationResId = locationResId,
                        launchpadNameResId = launchpadNameResId
                    )

                    Region(regionResId = regionResId)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TemperatureText(temperature = weather.temperature)

                    WeatherText(
                        weather = weather.summary
                    )

                    WindText(wind = weather.temperature)
                }
            }
        }
    }
}

@Composable
private fun FacilityLocation(
    @StringRes locationResId: Int,
    @StringRes launchpadNameResId: Int
) {
    Column {
        Text(
            text = stringResource(id = locationResId).uppercase(),
            color = Color.White
        )
        Text(
            text = stringResource(id = launchpadNameResId).uppercase(),
            color = Color.White
        )
    }
}

@Composable
private fun Region(
    @StringRes regionResId: Int
) {
    TitledText(titleResId = R.string.dashboard_region_title, contentResId = regionResId)
}

@Composable
private fun TemperatureText(
    temperature: String?
) {
    TitledText(
        titleResId = R.string.dashboard_temperature_title,
        content = temperature?.let {
            StringUtil.getString(
                R.string.dashboard_temperature_content,
                it
            )
        } ?: stringResource(id = R.string.dashboard_no_data))
}

@Composable
private fun WeatherText(
    weather: String
) {
    TitledText(titleResId = R.string.dashboard_weather_title, content = weather)
}

@Composable
private fun WindText(
    wind: String?
) {
    TitledText(
        titleResId = R.string.dashboard_wind_title,
        content = wind?.let {
            StringUtil.getString(
                R.string.dashboard_temperature_content,
                it
            )
        } ?: stringResource(id = R.string.dashboard_no_data))
}