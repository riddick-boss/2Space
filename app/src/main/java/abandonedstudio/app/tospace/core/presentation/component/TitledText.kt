package abandonedstudio.app.tospace.core.presentation.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
private fun BaseTitledText(
    @StringRes titleResId: Int,
    content: String,
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        Text(
            text = stringResource(id = titleResId).uppercase(),
            color = Color.LightGray
        )
        Text(
            text = content,
            color = Color.White
        )
    }
}

@Composable
fun TitledText(
    @StringRes titleResId: Int,
    @StringRes contentResId: Int
) {
    BaseTitledText(titleResId = titleResId, content = stringResource(id = contentResId))
}

@Composable
fun TitledText(
    @StringRes titleResId: Int,
    content: String
) {
    BaseTitledText(titleResId = titleResId, content = content)
}

@Composable
fun CenteredTitledText(
    @StringRes titleResId: Int,
    @StringRes contentResId: Int
) {
    BaseTitledText(
        titleResId = titleResId,
        content = stringResource(id = contentResId),
        horizontalAlignment = Alignment.CenterHorizontally
    )
}

@Composable
fun CenteredTitledText(
    @StringRes titleResId: Int,
    content: String
) {
    BaseTitledText(
        titleResId = titleResId,
        content = content,
        horizontalAlignment = Alignment.CenterHorizontally
    )
}