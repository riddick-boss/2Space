package abandonedstudio.app.tospace.features.news.presentation

import abandonedstudio.app.tospace.core.presentation.component.SwipeRefreshResultLazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ArticlesScreen(viewModel: NewsViewModel) {

    val articlesResult by viewModel.articles.collectAsStateWithLifecycle()

    val uriHandler = LocalUriHandler.current

    val onArticleClicked: (String) -> Unit = {
        uriHandler.openUri(it)
    }

    SwipeRefreshResultLazyColumn(
        result = articlesResult,
        onRefresh = viewModel::onArticlesRefresh,
        key = { it.hashCode() }
    ) {
        it.Item(onClick = onArticleClicked)
    }
}