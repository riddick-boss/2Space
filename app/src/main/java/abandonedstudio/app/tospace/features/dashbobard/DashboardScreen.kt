package abandonedstudio.app.tospace.features.dashbobard

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.domain.model.Launch
import abandonedstudio.app.tospace.core.domain.util.Precision
import abandonedstudio.app.tospace.core.domain.util.formatDateFromUnix
import abandonedstudio.app.tospace.core.presentation.component.TitledTextNoData
import abandonedstudio.app.tospace.core.presentation.theme.DarkGrayBackground
import abandonedstudio.app.tospace.core.presentation.util.contentDescription
import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
private fun NextLaunchCard(nextLaunch: Launch?, uriHandler: UriHandler, context: Context) {
    LaunchCard(titleResId = R.string.dashboard_upcoming_launch, launch = nextLaunch, uriHandler = uriHandler, context = context)
}

@Composable
private fun PreviousLaunchCard(previousLaunch: Launch?, uriHandler: UriHandler, context: Context) {
    LaunchCard(titleResId = R.string.dashboard_previous_launch, launch = previousLaunch, uriHandler = uriHandler, context = context)
}

@Composable
fun LaunchCard(@StringRes titleResId: Int, launch: Launch?, uriHandler: UriHandler, context: Context) {

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
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        TitledTextNoData(titleResId = R.string.dashboard_mission_name, content = launch.missionName)
                        TitledTextNoData(titleResId = R.string.dashboard_rocket, content = launch.rocket)
                        TitledTextNoData(titleResId = R.string.dashboard_flight_number, content = launch.flightNumber?.toString())
                        TitledTextNoData(titleResId = R.string.dashboard_time_local, content = launch.timeStamp?.formatDateFromUnix(Precision.MONTH)) // TODO
                    }

                    Column(modifier = Modifier.fillMaxWidth()) {
                        MissionLogo(logoImgPath = launch.logoImgPath)
                    }
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    Links(
                        wikipediaLink = launch.links.wikipedia,
                        ytLink = launch.links.yt,
                        redditLink = launch.links.reddit,
                        uriHandler = uriHandler,
                        context = context
                    )

                    TitledTextNoData(
                        titleResId = R.string.dashboard_launchpad,
                        content = launch.launchPad
                    )
                }

                ExpandableContent(
                    details = launch.details,
                    payloads = launch.payloads
                )
            }
        }
    }
}

@Composable
private fun ExpandableContent(
    details: String?,
    payloads: List<Launch.Payload>?
) {
    var expanded by remember{ mutableStateOf(false) }
    
    Column(
        modifier = Modifier
            .animateContentSize()
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.clickable { expanded = !expanded },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = if (expanded) R.string.dashboard_show_less else R.string.dashboard_show_more),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = contentDescription(),
                tint = Color.White,
                modifier = Modifier.rotate(if (expanded) 180f else 0f)
            )
        }

        if (expanded) {
            TitledTextNoData(titleResId = R.string.dashboard_details, content = details)

            if (!payloads.isNullOrEmpty()) {
                payloads.forEach {
                    PayloadCard(payload = it)
                }
            }
        }
    }
}

@Composable
private fun PayloadCard(payload: Launch.Payload) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.Transparent,
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            PayloadData(titleResId = R.string.dashboard_payload_type, content = payload.type)
            PayloadData(titleResId = R.string.dashboard_payload_mass, content = payload.massKg.toString())
            PayloadData(titleResId = R.string.dashboard_payload_orbit, content = payload.orbit)
            PayloadData(titleResId = R.string.dashboard_payload_inclination, content = payload.inclination.toString())
            PayloadData(titleResId = R.string.dashboard_payload_period, content = payload.periodMin.toString())
            payload.customers.forEach {
                PayloadData(titleResId = R.string.dashboard_payload_customer, content = it)
            }
        }
    }
}

@Composable
fun PayloadData(@StringRes titleResId: Int, content: String?) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = stringResource(titleResId),
                style = MaterialTheme.typography.body2,
                color = Color.White
            )
            Text(
                text = content ?: stringResource(id = R.string.no_data_info),
                style = MaterialTheme.typography.body2,
                color = Color.White
            )
        }

        Divider(color = Color.White, modifier = Modifier.fillMaxWidth(0.3f))
    }
}

@Composable
private fun Links(wikipediaLink: String?, ytLink: String?, redditLink: String?, uriHandler: UriHandler, context: Context) {
    Column(modifier = Modifier.fillMaxWidth(0.5f)) {
        Text(
            text = stringResource(id = R.string.dashboard_links).uppercase(),
            color = Color.LightGray
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Reference.WIKIPEDIA.LinkButton(uriHandler = uriHandler, context = context, link = wikipediaLink)
            Reference.YOUTUBE.LinkButton(uriHandler = uriHandler, context = context, link = ytLink)
            Reference.REDDIT.LinkButton(uriHandler = uriHandler, context = context, link = redditLink)
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
            .padding(16.dp)
            .aspectRatio(1f),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.DarkGray,
        elevation = 0.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
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