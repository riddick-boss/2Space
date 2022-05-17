package abandonedstudio.app.tospace.features.dashbobard

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.domain.model.Launch
import abandonedstudio.app.tospace.core.presentation.component.TitledTextNoData
import abandonedstudio.app.tospace.core.presentation.util.contentDescription
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter

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

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item("next_launch") {
            NextLaunchCard(nextLaunch)
        }

        item("previous_launch") {
            PreviousLaunchCard(previousLaunch)
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
private fun NextLaunchCard(nextLaunch: Launch?) {
    LaunchCard(titleResId = R.string.dashboard_upcoming_launch, launch = nextLaunch)
}

@Composable
private fun PreviousLaunchCard(previousLaunch: Launch?) {
    LaunchCard(titleResId = R.string.dashboard_previous_launch, launch = previousLaunch)
}

@Composable
fun LaunchCard(@StringRes titleResId: Int, launch: Launch?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.DarkGray
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

            if (launch == null) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(56.dp)
                        .align(Alignment.CenterHorizontally)
                )
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        TitledTextNoData(titleResId = R.string.dashboard_mission_name, content = launch.missionName)
                        TitledTextNoData(titleResId = R.string.dashboard_rocket, content = launch.rocket)
                        TitledTextNoData(titleResId = R.string.dashboard_flight_number, content = launch.flightNumber?.toString())
                        TitledTextNoData(titleResId = R.string.dashboard_time_local, content = launch.date)
                    }

                    Column(modifier = Modifier.fillMaxWidth()) {
                        MissionLogo(logoImgPath = launch.logoImgPath)
                    }
                }
            }
        }
    }
}

@Composable
private fun MissionLogo(
    logoImgPath: String?
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.DarkGray,
        elevation = 0.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = if (logoImgPath != null) rememberImagePainter(logoImgPath) else painterResource(id = R.drawable.spacex_logo),
                contentDescription = contentDescription(),
                contentScale = ContentScale.Fit
            )
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
        backgroundColor = Color.DarkGray
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