package abandonedstudio.app.tospace.features.news.data

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

data class Article(
    val title: String?,
    val summary: String?,
    val imageUrl: String?,
    val url: String?
) {

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun Item(onClick: (String) -> Unit) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            elevation = 0.dp,
            backgroundColor = DarkGrayBackground,
            onClick = { url?.also { onClick(it) } }
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
                            modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 250.dp),
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

                    title?.also {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.subtitle1,
                            color = Color.White,
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .fillMaxWidth()
                                .background(Brush.verticalGradient(listOf(Color.Black, Color.Transparent)))
                                .padding(8.dp)
                        )
                    }

                    summary?.also {
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
            }
        }
    }
}