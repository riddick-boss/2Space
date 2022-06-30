package abandonedstudio.app.tospace.features.news.presentation

import abandonedstudio.app.tospace.core.presentation.component.SwipeRefreshResultLazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalUriHandler

@Composable
fun EventsScreen(viewModel: NewsViewModel) {

    val eventsResult by viewModel.upcomingEventsFlow.collectAsState()

    val uriHandler = LocalUriHandler.current

    val onEventClicked: (String) -> Unit = {
        uriHandler.openUri(it)
    }
    
    SwipeRefreshResultLazyColumn(
        result = eventsResult,
        onRefresh = viewModel::onUpcomingEventsRefresh
    ) {
        it.Item(onClick = onEventClicked)
    }
}