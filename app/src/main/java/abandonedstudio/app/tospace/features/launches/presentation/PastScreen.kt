package abandonedstudio.app.tospace.features.launches.presentation

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.domain.model.PastSpaceXLaunch
import abandonedstudio.app.tospace.core.domain.util.resources.StringUtil
import abandonedstudio.app.tospace.core.presentation.component.SwipeRefreshPagingColumn
import abandonedstudio.app.tospace.core.presentation.component.TitledTextNoData
import abandonedstudio.app.tospace.core.presentation.theme.DarkGrayBackground
import abandonedstudio.app.tospace.core.presentation.util.contentDescription
import abandonedstudio.app.tospace.features.launches.data.Reference
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter

@Composable
fun PastScreen(
    viewModel: LaunchesViewModel
) {

    val launches = viewModel.pastLaunchesFlow.collectAsLazyPagingItems()

    SwipeRefreshPagingColumn(
        items = launches,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        PastLaunchCard(launch = it!!)
    }
}

@Composable
private fun PastLaunchCard(launch: PastSpaceXLaunch) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = DarkGrayBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TitledTextNoData(
                        titleResId = R.string.launches_mission_name,
                        content = launch.missionName
                    )

                    MissionStatus(success = launch.missionSuccess)

                    LandingStatus(success = launch.landingSuccess)
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MissionLogo(logoImgPath = launch.logoImgPath)
                }
            }

            Links(
                wikipediaLink = launch.links.wikipedia,
                ytLink = launch.links.yt,
                redditLink = launch.links.reddit
            )

            launch.details?.also {
                Text(
                    text = it,
                    style = MaterialTheme.typography.subtitle2,
                    color = Color.White
                )
            }

            InfoSection(
                titleResId = R.string.launches_past_launch,
                R.string.launches_past_date to launch.date,
                R.string.launches_past_launchpad to launch.launchPad
            )

            InfoSection(
                titleResId = R.string.launches_past_landpad,
                R.string.launches_past_landpad_name to launch.landPad.name,
                R.string.launches_past_landpad_full_name to launch.landPad.fullName,
                R.string.launches_past_landpad_region to launch.landPad.region
            )

            InfoSection(
                titleResId = R.string.launches_past_rocket,
                R.string.launches_past_rocket_name to launch.rocket,
                R.string.launches_past_rocket_reused to if (launch.core.reused == null) null else if (launch.core.reused) "Yes" else "No",
                R.string.launches_past_rocket_flightNum to launch.core.flightNum?.toString()
            )

            InfoSection(
                titleResId = R.string.launches_past_fairings,
                R.string.launches_past_fairings_recovered to if (launch.fairingsRecovered == null) null else if (launch.fairingsRecovered) "Yes" else "No"
            )

            launch.payloads.forEach { payload ->
                InfoSection(
                    titleResId = R.string.launches_past_payload,
                    R.string.launches_past_payload_type to payload.type,
                    R.string.launches_past_payload_mass to payload.massKg?.let { StringUtil.getString(R.string.launches_past_payload_mass_content, it) },
                    R.string.launches_past_payload_orbit to payload.orbit,
                    R.string.launches_past_payload_inclination to payload.inclination?.let { StringUtil.getString(R.string.launches_past_payload_inclination_content, it) },
                    R.string.launches_past_payload_period to payload.periodMin?.let { StringUtil.getString(R.string.launches_past_payload_period_content, it) },
                    R.string.launches_past_payload_apoapsis to payload.apoapsisKm?.let { StringUtil.getString(R.string.launches_past_payload_apoapsis_content, it) },
                    R.string.launches_past_payload_periapsis to payload.periapsisKm?.let { StringUtil.getString(R.string.launches_past_payload_periapsis_content, it) },
                    *payload.customers.mapNotNull { customer ->
                        R.string.launches_past_payload_customer to customer
                    }.toTypedArray()
                )
            }
        }
    }
}

@Composable
private fun InfoSection(@StringRes titleResId: Int, vararg params: Pair</*@StringRes*/ Int, String?>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = stringResource(id = titleResId),
            style = MaterialTheme.typography.body2,
            color = Color.White
        )
        Divider(color = Color.White, modifier = Modifier.fillMaxWidth(0.5f))
        params.forEach { (titleResId, content) ->
            ParamRow(titleResId = titleResId, content = content)
            Divider(color = Color.White, modifier = Modifier.fillMaxWidth(0.3f))
        }
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
fun ParamRow(@StringRes titleResId: Int, content: String?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = titleResId),
            style = MaterialTheme.typography.caption,
            color = Color.White
        )
        Text(
            text = content ?: stringResource(id = R.string.no_data_info),
            style = MaterialTheme.typography.caption,
            color = Color.White
        )
    }
}

@Composable
private fun Links(wikipediaLink: String?, ytLink: String?, redditLink: String?) {
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current

    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Reference.WIKIPEDIA.LinkButton(
            uriHandler = uriHandler,
            context = context,
            link = wikipediaLink
        )
        Reference.YOUTUBE.LinkButton(
            uriHandler = uriHandler,
            context = context,
            link = ytLink
        )
        Reference.REDDIT.LinkButton(
            uriHandler = uriHandler,
            context = context,
            link = redditLink
        )
    }
}

@Composable
private fun MissionStatus(success: Boolean?) {
    StatusCard(
        success = success,
        unknownResId = R.string.launches_mission_unknown,
        successResId = R.string.launches_mission_success,
        failResId = R.string.launches_mission_fail
    )
}

@Composable
private fun LandingStatus(success: Boolean?) {
    StatusCard(
        success = success,
        unknownResId = R.string.launches_landing_unknown,
        successResId = R.string.launches_landing_success,
        failResId = R.string.launches_landing_fail
    )
}

@Composable
private fun StatusCard(
    success: Boolean?,
    @StringRes unknownResId: Int,
    @StringRes successResId: Int,
    @StringRes failResId: Int,
) {
    Card(
        modifier = Modifier.fillMaxWidth(0.75f),
        shape = RoundedCornerShape(16.dp),
        elevation = 0.dp,
        backgroundColor = if (success == null) Color.Gray else if (success) Color.Green else Color.Red
    ) {
        Text(
            text = stringResource(
                if (success == null) unknownResId
                else if (success) successResId
                else failResId
            ),
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun MissionLogo(logoImgPath: String?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
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
                painter = if (logoImgPath == null) painterResource(id = R.drawable.spacex_logo) else rememberAsyncImagePainter(
                    model = logoImgPath
                ),
                contentDescription = contentDescription(),
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize().padding(8.dp)
            )
        }
    }
}