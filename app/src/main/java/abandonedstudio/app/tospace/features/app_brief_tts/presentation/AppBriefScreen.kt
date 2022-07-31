package abandonedstudio.app.tospace.features.app_brief_tts.presentation

import abandonedstudio.app.tospace.R
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.chargemap.compose.numberpicker.NumberPicker

@Composable
fun AppBriefScreen(
    viewModel: AppBriefViewModel = hiltViewModel()
) {
    val articlesToReadRange = 1..20

    val launchesStatus by viewModel.launchesStatus.collectAsState()
    val articlesStatus by viewModel.articlesStatus.collectAsState()
    val articlesToReadNumber by viewModel.articlesToReadNumber.collectAsState()
    val playAppBriefButtonVisible by viewModel.playAppBriefButtonVisible.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp),
    ) {
        Column(
            modifier = Modifier
                .weight(1.0f)
                .fillMaxSize(),
        ) {
            SwitchSetting(
                titleResId = R.string.app_brief_screen_launches_setting_title,
                enabled = launchesStatus,
                onCheckedChange = viewModel::onLaunchesStatusCheckedChange
            )

            SwitchSetting(
                titleResId = R.string.app_brief_screen_articles_setting_title,
                enabled = articlesStatus,
                onCheckedChange = viewModel::onArticlesStatusCheckedChange
            )

            if (articlesStatus) {
                NumberSetting(
                    titleResId = R.string.app_brief_screen_articles_number_setting_title,
                    number = articlesToReadNumber,
                    range = articlesToReadRange,
                    onNumberSelected = viewModel::onArticlesToReadNumberChange
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1.0f)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.app_brief_screen_playing_message),
                color = Color.Yellow,
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            if (playAppBriefButtonVisible) {
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
}

@Composable
private fun SwitchSetting(@StringRes titleResId: Int, enabled: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = titleResId),
            color = Color.White,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(end = 8.dp)
        )

        Switch(
            checked = enabled,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Yellow
            )
        )
    }
}

@Composable
private fun NumberSetting(@StringRes titleResId: Int, number: Int, range: Iterable<Int>, onNumberSelected: (Int) -> Unit) {
    var showNumberPickerDialog by remember { mutableStateOf(false) }
    var pickerValue by remember { mutableStateOf(number) }
    val onDismissRequest = { showNumberPickerDialog = false }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { showNumberPickerDialog = true }
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = titleResId),
            color = Color.White,
            style = MaterialTheme.typography.subtitle1
        )

        Text(
            text = number.toString(),
            color = Color.White,
            style = MaterialTheme.typography.subtitle1
        )
    }

    if (showNumberPickerDialog) {
        Dialog(onDismissRequest = onDismissRequest) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color.DarkGray
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(titleResId),
                        style = MaterialTheme.typography.subtitle2,
                        color = Color.White
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        NumberPicker(
                            value = pickerValue,
                            onValueChange = {
                                pickerValue = it
                            },
                            range = range,
                            textStyle = MaterialTheme.typography.subtitle1.copy(color = Color.White),
                            dividersColor = Color.Yellow
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = onDismissRequest) {
                            Text(text = stringResource(id = android.R.string.cancel), color = Color.White)
                        }
                        TextButton(onClick = {
                            onDismissRequest()
                            onNumberSelected(pickerValue)
                        }) {
                            Text(text = stringResource(id = android.R.string.ok), color = Color.White)
                        }
                    }
                }
            }
        }
    }
}