package abandonedstudio.app.tospace.features.app_brief_tts.presentation

import abandonedstudio.app.tospace.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AppBriefScreen(
    viewModel: AppBriefViewModel = hiltViewModel()
) {
    val playBriefButtonVisible by viewModel.playBriefButtonVisible

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (playBriefButtonVisible) {
            OutlinedButton(
                onClick = viewModel::onPlayBriefClicked,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                ),
                border = BorderStroke(2.dp, Color.Yellow),
                modifier = Modifier.fillMaxWidth(0.5f).padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.app_brief_screen_play_button),
                    color = Color.Yellow,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        } else {
            Text(
                text = stringResource(id = R.string.app_brief_screen_playing_message),
                color = Color.Yellow,
                style = MaterialTheme.typography.h6
            )
        }
    }
}