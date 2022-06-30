package abandonedstudio.app.tospace.features.news.data

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.presentation.theme.DarkGrayBackground
import abandonedstudio.app.tospace.core.presentation.util.contentDescription
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

data class Event(
    val title: String?,
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
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    imageUrl?.also {
                        SubcomposeAsyncImage(
                            model = it,
                            contentDescription = contentDescription(),
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.fillMaxWidth(),
                            loading = {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    CircularProgressIndicator(modifier = Modifier.size(58.dp))
                                }
                            }
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.TopCenter)
                            .background(Brush.verticalGradient(listOf(Color.Black, Color.Transparent)))
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        title?.also {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.subtitle1,
                                color = Color.White,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        type?.also {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.caption,
                                color = Color.White,
                            )
                        }
                    }

                    description?.also {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.subtitle2,
                            color = Color.White,
                            textAlign = TextAlign.Justify,
                            maxLines = 4,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black, Color.Black)))
                                .padding(8.dp)
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                    horizontalArrangement = if (newsUrl != null && videoUrl != null) Arrangement.SpaceBetween else Arrangement.End
                ) {
                    newsUrl?.also {
                        Button(
                            onClick = { onClick(it) },
                        ) {
                            Text(text = stringResource(id = R.string.news_read_more))
                        }
                    }

                    videoUrl?.also {
                        Button(
                            onClick = { onClick(it) },
                        ) {
                            Text(text = stringResource(id = R.string.news_watch))
                        }
                    }
                }
            }
        }
    }
}