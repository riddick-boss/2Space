package abandonedstudio.app.tospace.features.news.presentation

import abandonedstudio.app.tospace.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun ArticlesScreen(viewModel: NewsViewModel) {

    val articlesResult by viewModel.articles.collectAsState()

    val uriHandler = LocalUriHandler.current

    val onArticleClicked: (String) -> Unit = {
        uriHandler.openUri(it)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        articlesResult.also { result ->
            if (result == null) {
                CircularProgressIndicator(
                    modifier = Modifier.size(56.dp)
                )
            } else if (result.isFailure) {
                Text(
                    text = stringResource(id = R.string.news_failed_to_load_data),
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.White
                )
            } else {
                result.getOrNull()?.also { events ->
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(events) {
                            it.Item(onClick = onArticleClicked)
                        }
                    }
                }
            }
        }
    }
}