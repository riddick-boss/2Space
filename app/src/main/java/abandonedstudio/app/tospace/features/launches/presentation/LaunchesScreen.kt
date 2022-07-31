package abandonedstudio.app.tospace.features.launches.presentation

import abandonedstudio.app.tospace.core.presentation.component.SwipeRefreshPagingColumn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun LaunchesScreen(
    viewModel: LaunchesViewModel = hiltViewModel()
) {

    val launches = viewModel.upcomingLaunchesFlow.collectAsLazyPagingItems()

    SwipeRefreshPagingColumn(
        items = launches,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        key = { it.hashCode() }
    ) {
        it!!.Item()
    }
}