package abandonedstudio.app.tospace.features.news.presentation

import abandonedstudio.app.tospace.R
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable

enum class Tab(@StringRes val titleResId: Int, val content: @Composable (viewModel: NewsViewModel) -> Unit) {

    EVENTS(
        titleResId = R.string.news_events_title,
        content = { EventsScreen(viewModel = it) }
    ),

    ARTICLES(
        titleResId = R.string.news_articles_title,
        content = { ArticlesScreen(viewModel = it) }
    );
}