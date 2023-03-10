package abandonedstudio.app.tospace.features.launches.data

import abandonedstudio.app.tospace.domain.model.util.DateFormat
import abandonedstudio.app.tospace.domain.ui.theme.DarkGrayBackground
import abandonedstudio.app.tospace.domain.ui.util.contentDescription
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

data class UpcomingLaunch(
    val id: String?,
    val name: String?,
    val status: LaunchStatus?,
    val launchPad: LaunchPad?,
    val description: String?,
    val imageUrl: String?,
    val infographicUrl: String?,
    val probability: Int?,
    val timeStamp: DateFormat? // TODO: String?
) {

    data class LaunchStatus(
        val id: Int?,
        val name: String?,
        val shortName: String?,
        val description: String?
    )

    data class LaunchPad(
        val name: String?,
        val location: String?
    )

    @Composable
    fun Item() {
        UpcomingLaunchCard(this)
    }
}

@Composable
private fun UpcomingLaunchCard(launch: UpcomingLaunch) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = DarkGrayBackground
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {


            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                launch.imageUrl?.also {
                    SubcomposeAsyncImage(
                        model = it,
                        contentDescription = contentDescription(),
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)),
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

                launch.name?.also {
                    Text(
                        text = it,
                        color = Color.White,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
                }

                launch.timeStamp?.format(DateFormat.Precision.YEAR)?.also {
                    Text(
                        text = it,
                        color = Color.White,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                }

                launch.description?.also {
                    Text(
                        text = it,
                        color = Color.White,
                        style = MaterialTheme.typography.subtitle2
                    )
                }
            }
        }
    }
}