package abandonedstudio.app.tospace.features.launches.presentation

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.domain.model.PastSpaceXLaunch
import abandonedstudio.app.tospace.core.presentation.component.SwipeRefreshPagingColumn
import abandonedstudio.app.tospace.core.presentation.component.TitledTextNoData
import abandonedstudio.app.tospace.core.presentation.theme.DarkGrayBackground
import abandonedstudio.app.tospace.core.presentation.util.contentDescription
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
                .padding(8.dp)
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
        }
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
                painter = if (logoImgPath == null) painterResource(id = R.drawable.spacex_logo) else rememberAsyncImagePainter(
                    model = logoImgPath
                ),
                contentDescription = contentDescription(),
                contentScale = ContentScale.Fit
            )
        }
    }
}