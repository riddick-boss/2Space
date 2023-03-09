package abandonedstudio.app.tospace.domain.ui.component

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.domain.infrastructure.StringUtil
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun <T: Any> SwipeRefreshPagingColumn(
    items: LazyPagingItems<T>,
    key: ((item: T) -> Any)? = null,
    contentPadding: PaddingValues,
    verticalArrangement: Arrangement.Vertical,
    itemContent: @Composable LazyItemScope.(item: T?) -> Unit
) {
    var isRefreshing by remember { mutableStateOf(false) }

    items.loadState.also {
        showErrorToastIfNecessary(it, LocalContext.current)
    }

    if (items.loadState.refresh !is LoadState.Loading) {
        isRefreshing = false
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        modifier = Modifier.fillMaxSize(),
        onRefresh = {
            isRefreshing = true
            items.refresh()
        }
    ) {
        if (!isRefreshing && items.itemSnapshotList.isEmpty() && items.loadState.refresh is LoadState.Loading) {
            InitialProgressBar()
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = contentPadding,
            verticalArrangement = verticalArrangement
        ) {
            items(items = items, key = key, itemContent = itemContent)

            if (items.loadState.append is LoadState.Loading) {
                item("append_bar") {
                    AppendProgressBar()
                }
            }
        }
    }
}

@Composable
private fun InitialProgressBar() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun AppendProgressBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

private fun showErrorToastIfNecessary(states: CombinedLoadStates, context: Context) {
    if (states.append is LoadState.Error || states.prepend is LoadState.Error || states.refresh is LoadState.Error) {
        Toast.makeText(context, StringUtil.getString(R.string.fetch_fail_message), Toast.LENGTH_SHORT).show()
    }
}