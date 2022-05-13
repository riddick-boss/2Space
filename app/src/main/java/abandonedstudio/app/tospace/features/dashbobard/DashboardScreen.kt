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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel()
) {

    val capeCanaveralWeather by viewModel.capeCanaveralWeather.collectAsState()
    val starBaseWeather by viewModel.starBaseWeather.collectAsState()

    val showWeatherLoading by viewModel.showWeatherLoading.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item("weather_card") {
            Card(
                modifier = Modifier.animateContentSize(),
                elevation = 0.dp,
                shape = RoundedCornerShape(8.dp)
            ) {
                if (!showWeatherLoading) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Facility.CAPE_CANAVERAL.Content(weather = capeCanaveralWeather!!)
//                        TODO: starbase
//                        TODO: vanden
                    }
                } else {
                    CircularProgressIndicator(modifier = Modifier.size(56.dp))
                }
            }
        }
    }
}