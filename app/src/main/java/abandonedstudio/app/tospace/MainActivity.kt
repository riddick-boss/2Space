package abandonedstudio.app.tospace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import abandonedstudio.app.tospace.domain.ui.navigation.MainDrawer
import abandonedstudio.app.tospace.domain.ui.theme.ToSpaceTheme
import abandonedstudio.app.tospace.domain.ui.util.contentDescription
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Pawel Kremienowski <Kremienowski33@gmail.com>
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToSpaceTheme {
                App()
            }
        }
    }
}

@Composable
private fun App() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.spacex_background),
            contentDescription = contentDescription(),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        MainDrawer()
    }
}