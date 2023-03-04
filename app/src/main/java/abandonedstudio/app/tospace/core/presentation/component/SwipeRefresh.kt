package abandonedstudio.app.tospace.core.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeRefresh(isRefreshing: Boolean, pullRefreshState: PullRefreshState, content: @Composable () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth().pullRefresh(pullRefreshState)
    ) {
        content()

        SwipeRefreshIndicator(isRefreshing, pullRefreshState)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BoxScope.SwipeRefreshIndicator(isRefreshing: Boolean, pullRefreshState: PullRefreshState) {
    if (isRefreshing || pullRefreshState.progress > 0) {
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}