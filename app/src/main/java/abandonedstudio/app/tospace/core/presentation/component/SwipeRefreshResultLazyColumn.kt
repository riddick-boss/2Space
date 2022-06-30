package abandonedstudio.app.tospace.core.presentation.component

import abandonedstudio.app.tospace.core.domain.model.util.Result
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun <DATA, RESULT: Result<List<DATA>>> SwipeRefreshResultLazyColumn(
    result: RESULT?,
    modifier: Modifier = Modifier.fillMaxSize(),
    paddingValues: PaddingValues = PaddingValues(16.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(16.dp),
    onRefresh: () -> Unit,
    content: @Composable (data: DATA) -> Unit
) {

    var isRefreshing by remember { mutableStateOf(false) }

    result?.also {
        isRefreshing = false
    }

    result.also {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            modifier = modifier,
            onRefresh = {
                isRefreshing = true
                onRefresh()
            }
        ) {
            if (it == null) {
                if (!isRefreshing) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(56.dp)
                        )
                    }
                }
            } else {
                it.getOrNull().also { list ->
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = paddingValues,
                        verticalArrangement = verticalArrangement
                    ) {
                        list?.also {
                            items(list) { item ->
                                content(item)
                            }
                        } ?: item("empty_item") {
                            // empty item, seems that swipe to refresh needs it
                            // otherwise if list is empty we cannot refresh
                        }
                    }
                }
            }
        }
    }
}