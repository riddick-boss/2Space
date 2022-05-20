package abandonedstudio.app.tospace.features.dashbobard.data

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.presentation.component.CenteredTitledText
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

enum class Facility(
    @StringRes val locationResId: Int,
    @StringRes val regionResId: Int,
    @StringRes val launchpadNameResId: Int,
    @DrawableRes val imageResId: Int
) {

    CAPE_CANAVERAL(
        locationResId = R.string.dashboard_canaveral_location,
        regionResId = R.string.dashboard_canaveral_region,
        launchpadNameResId = R.string.dashboard_canaveral_launchpad,
        imageResId = R.drawable.canaveral
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
    },

    STARBASE(
        locationResId = R.string.dashboard_starship_location,
        regionResId = R.string.dashboard_starship_region,
        launchpadNameResId = R.string.dashboard_starship_launchpad,
        imageResId = R.drawable.starbase
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
    },

    VANDENBERG(
        locationResId = R.string.dashboard_vandenberg_location,
        regionResId = R.string.dashboard_vandenberg_region,
        launchpadNameResId = R.string.dashboard_vandenberg_launchpad,
        imageResId = R.drawable.vandenberg
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
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp),
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
                    .padding(8.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
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

                    WindText(wind = weather.wind)
                }
            }
        }
    }
}

@Composable
private fun RowScope.FacilityLocation(
    @StringRes locationResId: Int,
    @StringRes launchpadNameResId: Int
) {
    Column(
        modifier = Modifier.padding(end = 5.dp).weight(1f)
    ) {
        Text(
            text = stringResource(id = locationResId).uppercase(),
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = stringResource(id = launchpadNameResId).uppercase(),
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
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
    temperature: String
) {
    CenteredTitledText(
        titleResId = R.string.dashboard_temperature_title,
        content = temperature
    )
}

@Composable
private fun WeatherText(
    weather: String
) {
    CenteredTitledText(titleResId = R.string.dashboard_weather_title, content = weather)
}

@Composable
private fun WindText(
    wind: String
) {
    CenteredTitledText(
        titleResId = R.string.dashboard_wind_title,
        content = wind
    )
}