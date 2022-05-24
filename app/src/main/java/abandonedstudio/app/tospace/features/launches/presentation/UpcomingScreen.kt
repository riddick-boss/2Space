package abandonedstudio.app.tospace.features.launches.presentation

import abandonedstudio.app.tospace.core.presentation.component.SwipeRefreshPagingColumn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems

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
//        TODO
    }
}