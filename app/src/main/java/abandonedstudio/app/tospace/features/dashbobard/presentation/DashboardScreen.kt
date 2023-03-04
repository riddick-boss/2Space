package abandonedstudio.app.tospace.features.dashbobard.presentation

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.presentation.component.SwipeRefresh
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
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel()
) {

    val nextLaunch by viewModel.nextLaunchFlow.collectAsStateWithLifecycle()
    val nextLaunchCountdown by viewModel.nextLaunchCountdown.collectAsStateWithLifecycle()
    val previousLaunch by viewModel.previousLaunchFlow.collectAsStateWithLifecycle()
    val previousLaunchCountdown by viewModel.previousLaunchCountdown.collectAsStateWithLifecycle()

    val capeCanaveralWeather by viewModel.capeCanaveralWeather.collectAsStateWithLifecycle()
    val starbaseWeather by viewModel.starbaseWeather.collectAsStateWithLifecycle()
    val vandenbergWeather by viewModel.vandenbergWeather.collectAsStateWithLifecycle()

    val showWeatherContent by viewModel.showWeatherContent.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current

    var isRefreshing by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.refreshCompletedFlow.onEach {
            isRefreshing = false
        }.launchIn(this)
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            viewModel.onRefresh()
        })

    SwipeRefresh(isRefreshing = isRefreshing, pullRefreshState = pullRefreshState) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item("next_launch") {
                NextLaunchCard(
                    nextLaunch = nextLaunch,
                    countDown = nextLaunchCountdown,
                    uriHandler = uriHandler,
                    context = context
                )
            }

            item("previous_launch") {
                PreviousLaunchCard(
                    previousLaunch = previousLaunch,
                    countDown = previousLaunchCountdown,
                    uriHandler = uriHandler,
                    context = context
                )
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
}

@Composable
private fun NextLaunchCard(
    nextLaunch: Result<SpaceXLaunch>?,
    countDown: String,
    uriHandler: UriHandler,
    context: Context
) {
    LaunchCard(
        titleResId = R.string.dashboard_upcoming_launch,
        launchResult = nextLaunch,
        countDown = countDown,
        uriHandler = uriHandler,
        context = context
    )
}

@Composable
private fun PreviousLaunchCard(
    previousLaunch: Result<SpaceXLaunch>?,
    countDown: String,
    uriHandler: UriHandler,
    context: Context
) {
    LaunchCard(
        titleResId = R.string.dashboard_previous_launch,
        launchResult = previousLaunch,
        countDown = countDown,
        uriHandler = uriHandler,
        context = context
    )
}

@Composable
private fun LaunchCard(
    @StringRes titleResId: Int,
    launchResult: Result<SpaceXLaunch>?,
    countDown: String,
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

            Text(
                text = countDown,
                style = MaterialTheme.typography.body1,
                color = Color.Yellow,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )


            if (launchResult == null) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(56.dp)
                        .align(Alignment.CenterHorizontally)
                )
            } else if (launchResult.isFailure) {
                Text(
                    text = stringResource(id = R.string.dashboard_failed_to_load_launch_info),
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.White
                )
            } else {
                launchResult.getOrNull()?.Item(uriHandler = uriHandler, context = context)
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