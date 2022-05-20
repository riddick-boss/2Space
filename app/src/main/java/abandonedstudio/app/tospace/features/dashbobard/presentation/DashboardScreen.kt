package abandonedstudio.app.tospace.features.dashbobard.presentation

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.presentation.theme.DarkGrayBackground
import abandonedstudio.app.tospace.features.dashbobard.data.Facility
import abandonedstudio.app.tospace.features.dashbobard.data.FacilityWeather
import abandonedstudio.app.tospace.features.dashbobard.data.SpaceXLaunch
import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel()
) {

    val nextLaunch by viewModel.nextLaunchFlow.collectAsState()
    val previousLaunch by viewModel.previousLaunchFlow.collectAsState()

    val capeCanaveralWeather by viewModel.capeCanaveralWeather.collectAsState()
    val starbaseWeather by viewModel.starbaseWeather.collectAsState()
    val vandenbergWeather by viewModel.vandenbergWeather.collectAsState()

    val showWeatherContent by viewModel.showWeatherContent.collectAsState()

    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item("next_launch") {
            NextLaunchCard(nextLaunch, uriHandler, context)
        }

        item("previous_launch") {
            PreviousLaunchCard(previousLaunch, uriHandler, context)
        }

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
private fun NextLaunchCard(nextLaunch: SpaceXLaunch?, uriHandler: UriHandler, context: Context) {
    LaunchCard(titleResId = R.string.dashboard_upcoming_launch, launch = nextLaunch, uriHandler = uriHandler, context = context)
}

@Composable
private fun PreviousLaunchCard(previousLaunch: SpaceXLaunch?, uriHandler: UriHandler, context: Context) {
    LaunchCard(titleResId = R.string.dashboard_previous_launch, launch = previousLaunch, uriHandler = uriHandler, context = context)
}

@Composable
private fun LaunchCard(
    @StringRes titleResId: Int,
    launch: SpaceXLaunch?,
    uriHandler: UriHandler,
    context: Context
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = DarkGrayBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(id = titleResId),
                style = MaterialTheme.typography.h5,
                color = Color.White
            )
            //TODO: T-counter


            if (launch == null) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(56.dp)
                        .align(Alignment.CenterHorizontally)
                )
            } else {
                launch.Item(launch = launch, uriHandler = uriHandler, context = context)
            }
        }
    }
}

@Composable
private fun FacilitiesCard(
    showContent: Boolean,
    capeCanaveralWeather: FacilityWeather?,
    starbaseWeather: FacilityWeather?,
    vandenbergWeather: FacilityWeather?
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = DarkGrayBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.dashboard_launch_facilities),
                style = MaterialTheme.typography.h5,
                color = Color.White
            )

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