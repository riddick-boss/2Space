package abandonedstudio.app.tospace.features.dashbobard

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.domain.util.extension.showToast
import abandonedstudio.app.tospace.core.presentation.util.contentDescription
import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

enum class Reference(@DrawableRes val iconResId: Int) {

    WIKIPEDIA(R.drawable.wikipedia_logo),
    YOUTUBE(R.drawable.youtube_logo),
    REDDIT(R.drawable.reddit_logo);

    private fun onIconClick(uriHandler: UriHandler, context: Context, link: String?) {
        if (link.isNullOrBlank()) {
            context.showToast(R.string.dashboard_reference_no_link)
        } else {
            uriHandler.openUri(link)
        }
    }

    @Composable
    fun LinkButton(uriHandler: UriHandler, context: Context, link: String?) {
        Button(
            modifier = Modifier.size(30.dp),
            shape = CircleShape,
            contentPadding = PaddingValues(4.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.DarkGray
            ),
            onClick = {
            onIconClick(uriHandler, context, link)
        }
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = contentDescription()
            )
        }
    }

}