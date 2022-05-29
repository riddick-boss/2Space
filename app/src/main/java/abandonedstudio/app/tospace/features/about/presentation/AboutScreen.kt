package abandonedstudio.app.tospace.features.about.presentation

import abandonedstudio.app.tospace.BuildConfig
import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.domain.util.resources.StringUtil
import abandonedstudio.app.tospace.core.presentation.theme.HyperLinkColor
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private object Links {
    const val SPACEX_API_LINK = "https://github.com/r-spacex/SpaceX-API"
    const val OPEN_WEATHER_MAP_API_LINK = "https://openweathermap.org/"
}

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DisclaimerItem()
        MentionItem()
        ContactItem()
    }
}

@Composable
private fun DisclaimerItem() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.about_disclaimer),
            style = MaterialTheme.typography.h6,
            color = Color.White
        )
        Text(
            text = stringResource(id = R.string.about_disclaimer_content),
            style = MaterialTheme.typography.subtitle2,
            textAlign = TextAlign.Justify,
            color = Color.White
        )
    }
}

@Composable
private fun MentionItem() {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.about_mention),
            style = MaterialTheme.typography.subtitle1,
            color = Color.White
        )
        HyperLinkText(
            textResId = R.string.about_mention_spacex_api,
            link = Links.SPACEX_API_LINK,
            uriHandler = uriHandler
        )
        HyperLinkText(
            textResId = R.string.about_mention_open_weather_map_api,
            link = Links.OPEN_WEATHER_MAP_API_LINK,
            uriHandler = uriHandler
        )
    }
}

@Composable
private fun HyperLinkText(@StringRes textResId: Int, link: String, uriHandler: UriHandler) {
    Text(
        text = stringResource(id = textResId),
        fontSize = 14.sp,
        color = HyperLinkColor,
        fontStyle = FontStyle.Italic,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier.clickable { uriHandler.openUri(link) }
    )
}

@Composable
private fun ContactItem() {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.about_issue_title),
            style = MaterialTheme.typography.subtitle1,
            color = Color.White
        )
        BuildConfig.EMAIL.also {
            if (it.isNotBlank()) {
                Text(
                    text = it,
                    fontSize = 14.sp,
                    color = HyperLinkColor,
                    fontStyle = FontStyle.Italic,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable { composeEmail(context, arrayOf(it)) }
                )
            } else {
                Text(
                    text = stringResource(id = R.string.no_email_provided),
                    style = MaterialTheme.typography.subtitle2,
                    color = Color.White
                )
            }
        }
    }
}

private fun composeEmail(context: Context, emails: Array<String>) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:") // only email apps
        putExtra(Intent.EXTRA_EMAIL, emails)
        putExtra(Intent.EXTRA_SUBJECT, StringUtil.getString(R.string.about_email_subject))
    }
    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(
            context,
            StringUtil.getString(R.string.cannot_send_email_info),
            Toast.LENGTH_SHORT
        ).show()
    }
}