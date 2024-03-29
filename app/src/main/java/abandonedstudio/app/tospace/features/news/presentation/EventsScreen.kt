package abandonedstudio.app.tospace.features.news.presentation

import abandonedstudio.app.tospace.domain.ui.component.SwipeRefreshResultLazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun EventsScreen(viewModel: NewsViewModel) {

    val eventsResult by viewModel.upcomingEventsFlow.collectAsStateWithLifecycle()

    val uriHandler = LocalUriHandler.current

    val onEventClicked: (String) -> Unit = {
        uriHandler.openUri(it)
    }
    
    SwipeRefreshResultLazyColumn(
        result = eventsResult,
        onRefresh = viewModel::onUpcomingEventsRefresh,
        key = { it.hashCode() }
    ) {
        it.Item(onClick = onEventClicked)
    }
}