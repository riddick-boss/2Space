package abandonedstudio.app.tospace.features.launches.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items

@Composable
fun LaunchesScreen(
    viewModel: LaunchesViewModel = hiltViewModel()
) {

    val upcomingLaunches = viewModel.upcomingLaunchesFlow.collectAsLazyPagingItems()

    val pastLaunches = viewModel.pastLaunchesFlow.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(pastLaunches) {
            Titlewd(name = it?.missionName)
        }
    }
}

@Composable
fun Titlewd(name: String?) {
    Text(text = name.orEmpty(), color = Color.White, modifier = Modifier
        .height(50.dp)
        .background(
            Color.Blue
        ))
}