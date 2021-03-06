package abandonedstudio.app.tospace.features.spacex.presentation

import abandonedstudio.app.tospace.R
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable

enum class Tab(@StringRes val titleResId: Int, val content: @Composable (viewModel: SpaceXViewModel) -> Unit) {

    UPCOMING(
      titleResId = R.string.launches_upcoming_title,
      content = { UpcomingScreen(viewModel = it) }
    ),

    PAST(
        titleResId = R.string.launches_past_title,
        content = { PastScreen(viewModel = it) }
    );
}