package abandonedstudio.app.tospace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import abandonedstudio.app.tospace.core.presentation.theme.ToSpaceTheme
import abandonedstudio.app.tospace.core.presentation.util.contentDescription
import abandonedstudio.app.tospace.navigation.maindrawer.MainDrawer
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.res.painterResource

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
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // TODO: icon
            contentDescription = contentDescription(),
            modifier = Modifier.fillMaxSize()
        )
        MainDrawer()
    }
}