package abandonedstudio.app.tospace.features.dashbobard.data

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.domain.util.resources.StringUtil
import abandonedstudio.app.tospace.core.presentation.component.TitledTextNoData
import abandonedstudio.app.tospace.core.presentation.util.contentDescription
import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.*

data class SpaceXLaunch(
    val missionName: String?,
    val logoImgPath: String?,
    val rocket: String?,
    val flightNumber: Int?,
    val timeStamp: Int?,
    val date: String?,
    val links: Links,
    val details: String?,
    val launchPad: String?,
    val payloads: List<Payload>
) {
    private var expanded by mutableStateOf(false)

    private val toggleExpand = { expanded = !expanded }

    @Composable
    fun Item(uriHandler: UriHandler, context: Context) {
        SpacexLaunchCard(
            launch = this,
            uriHandler = uriHandler,
            context = context,
            expanded = expanded,
            onExpandClicked = toggleExpand
        )
    }

    data class Links(
        val wikipedia: String?,
        val yt: String?,
        val reddit: String?
    )

    data class Payload(
        val type: String?,
        val massKg: Int?,
        val orbit: String?,
        val inclination: Double?,
        val periodMin: Double?,
        val customers: List<String?>
    )
}

@Composable
private fun SpacexLaunchCard(
    launch: SpaceXLaunch,
    uriHandler: UriHandler,
    context: Context,
    expanded: Boolean,
    onExpandClicked: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxWidth(0.5f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TitledTextNoData(
                titleResId = R.string.dashboard_mission_name,
                content = launch.missionName
            )

            TitledTextNoData(titleResId = R.string.dashboard_rocket, content = launch.rocket)

            TitledTextNoData(
                titleResId = R.string.dashboard_flight_number,
                content = launch.flightNumber?.let {
                    StringUtil.getString(R.string.dashboard_flight_number_content, it)
                })

            TitledTextNoData(titleResId = R.string.dashboard_time_local, content = launch.date)
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
        payloads = launch.payloads,
        expanded = expanded,
        onExpandClicked = onExpandClicked
    )
}

@Composable
private fun ExpandableContent(
    details: String?,
    payloads: List<SpaceXLaunch.Payload>?,
    expanded: Boolean,
    onExpandClicked: () -> Unit
) {

    Column(
        modifier = Modifier
            .animateContentSize()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = onExpandClicked)
                .padding(vertical = 8.dp),
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
                Text(
                    text = stringResource(id = R.string.dashboard_payloads).uppercase(),
                    color = Color.LightGray
                )

                payloads.forEach {
                    PayloadCard(payload = it)
                }
            }
        }
    }
}

@Composable
private fun PayloadCard(payload: SpaceXLaunch.Payload) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.Transparent,
        shape = RectangleShape,
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            PayloadData(titleResId = R.string.dashboard_payload_type, content = payload.type)

            PayloadData(
                titleResId = R.string.dashboard_payload_mass,
                content = payload.massKg?.let {
                    StringUtil.getString(R.string.dashboard_payload_mass_content, it)
                })

            PayloadData(titleResId = R.string.dashboard_payload_orbit, content = payload.orbit)

            PayloadData(
                titleResId = R.string.dashboard_payload_inclination,
                content = payload.inclination?.let {
                    StringUtil.getString(R.string.dashboard_payload_inclination_content, it)
                })

            PayloadData(
                titleResId = R.string.dashboard_payload_period,
                content = payload.periodMin?.let {
                    StringUtil.getString(R.string.dashboard_payload_period_content, it)
                })

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
private fun Links(
    wikipediaLink: String?,
    ytLink: String?,
    redditLink: String?,
    uriHandler: UriHandler,
    context: Context
) {
    Column(modifier = Modifier.fillMaxWidth(0.5f)) {
        Text(
            text = stringResource(id = R.string.dashboard_links).uppercase(),
            color = Color.LightGray
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Reference.WIKIPEDIA.LinkButton(
                uriHandler = uriHandler,
                context = context,
                link = wikipediaLink
            )
            Reference.YOUTUBE.LinkButton(uriHandler = uriHandler, context = context, link = ytLink)
            Reference.REDDIT.LinkButton(
                uriHandler = uriHandler,
                context = context,
                link = redditLink
            )
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
            if (logoImgPath == null) {
                Image(
                    painter = painterResource(id = R.drawable.spacex_logo),
                    contentDescription = contentDescription(),
                    contentScale = ContentScale.Fit
                )
            } else {
                SubcomposeAsyncImage(
                    model = logoImgPath,
                    contentDescription = contentDescription(),
                    contentScale = ContentScale.Fit
                ) {
                    val state = painter.state
                    if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                        CircularProgressIndicator()
                    } else {
                        SubcomposeAsyncImageContent()
                    }
                }
            }
        }
    }
}