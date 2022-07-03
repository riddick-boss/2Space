package abandonedstudio.app.tospace.features.spacex.presentation

import abandonedstudio.app.tospace.core.presentation.component.SwipeRefreshPagingColumn
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun PastScreen(
    viewModel: SpaceXViewModel
) {

    val launches = viewModel.pastLaunchesFlow.collectAsLazyPagingItems()

    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current

    SwipeRefreshPagingColumn(
        items = launches,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        it!!.Item(uriHandler = uriHandler, context = context)
    }
}

