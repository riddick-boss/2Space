package abandonedstudio.app.tospace.features.news.data

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.presentation.theme.DarkGrayBackground
import abandonedstudio.app.tospace.core.presentation.util.contentDescription
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent

data class Event(
    val name: String?,
    val description: String?,
    val imageUrl: String?,
    val newsUrl: String?,
    val videoUrl: String?,
    val type: String?
) {

    @Composable
    fun Item(onClick: (String) -> Unit) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            elevation = 0.dp,
            backgroundColor = DarkGrayBackground
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                imageUrl?.also {
                    SubcomposeAsyncImage(
                        model = it,
                        contentDescription = contentDescription(),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val state = painter.state
                        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                            CircularProgressIndicator(modifier = Modifier.size(58.dp))
                        } else {
                            SubcomposeAsyncImageContent()
                        }
                    }
                }

                type?.also {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.caption,
                        color = Color.White,
                    )
                }

                name?.also {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.White
                    )
                }

                description?.also {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.subtitle2,
                        color = Color.White,
                        textAlign = TextAlign.Justify
                    )
                }

                newsUrl?.also {
                    Button(
                        onClick = { onClick(it) },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = stringResource(id = R.string.news_read_more))
                    }
                }

                videoUrl?.also {
                    Button(
                        onClick = { onClick(it) },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = stringResource(id = R.string.news_watch))
                    }
                }
            }
        }
    }
}