package abandonedstudio.app.tospace.features.dashbobard

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel()
) {

    val capeCanaveralWeather by viewModel.capeCanaveralWeather.collectAsState()
    val starbaseWeather by viewModel.starbaseWeather.collectAsState()
    val vandenbergWeather by viewModel.vandenbergWeather.collectAsState()

    val showWeatherContent by viewModel.showWeatherContent.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item("facilities_card") {
            FacilitiesCard(
                showContent = showWeatherContent,
                capeCanaveralWeather = capeCanaveralWeather,
                starbaseWeather = starbaseWeather,
                vandenbergWeather = vandenbergWeather
            )
        }
    }
}

@Composable
fun FacilitiesCard(
    showContent: Boolean,
    capeCanaveralWeather: FacilityWeather?,
    starbaseWeather: FacilityWeather?,
    vandenbergWeather: FacilityWeather?
) {
    Card(
        modifier = Modifier.animateContentSize(),
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            if (showContent) {
                capeCanaveralWeather?.also {
                    Facility.CAPE_CANAVERAL.Content(weather = it)
                }
                starbaseWeather?.also {
                    Facility.STARBASE.Content(weather = it)
                }
                vandenbergWeather?.also {
                    Facility.VANDENBERG.Content(weather = it)
                }
            } else {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(56.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}