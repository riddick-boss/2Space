package abandonedstudio.app.tospace.features.spacex.presentation

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.domain.model.spacex.UpcomingSpaceXLaunch
import abandonedstudio.app.tospace.core.domain.util.resources.StringUtil
import abandonedstudio.app.tospace.core.presentation.component.SwipeRefreshPagingColumn
import abandonedstudio.app.tospace.core.presentation.component.TitledTextNoData
import abandonedstudio.app.tospace.core.presentation.theme.DarkGrayBackground
import abandonedstudio.app.tospace.core.presentation.util.contentDescription
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter

@Composable
fun UpcomingScreen(
    viewModel: LaunchesViewModel
) {

    val launches = viewModel.upcomingLaunchesFlow.collectAsLazyPagingItems()

    SwipeRefreshPagingColumn(
        items = launches,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        UpcomingLaunchCard(launch = it!!)
    }
}

@Composable
private fun UpcomingLaunchCard(launch: UpcomingSpaceXLaunch) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = DarkGrayBackground
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.5f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TitledTextNoData(
                    titleResId = R.string.launches_mission_name,
                    content = launch.missionName
                )

                TitledTextNoData(titleResId = R.string.launches_rocket, content = launch.rocket)

                TitledTextNoData(
                    titleResId = R.string.launches_time_local,
                    content = launch.date?.let {
                        if (launch.net) {
                            StringUtil.getString(R.string.launches_net_time_content, it)
                        } else {
                            it
                        }
                    }
                )
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
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = if (logoImgPath == null) painterResource(id = R.drawable.spacex_logo) else rememberAsyncImagePainter(model = logoImgPath),
                contentDescription = contentDescription(),
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize().padding(8.dp)
            )
        }
    }
}