package abandonedstudio.app.tospace.features.app_brief_tts.presentation

import abandonedstudio.app.tospace.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AppBriefScreen(
    viewModel: AppBriefViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.app_brief_screen_playing_message),
                color = Color.Yellow,
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Justify
            )

            OutlinedButton(
                onClick = viewModel::onPlayBriefClicked,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                ),
                border = BorderStroke(2.dp, Color.Yellow),
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(24.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.app_brief_screen_play_button),
                    color = Color.Yellow,
                    style = MaterialTheme.typography.h5
                )
            }
        }
    }
}