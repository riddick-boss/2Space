package abandonedstudio.app.tospace.domain.ui.navigation

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.domain.ui.util.contentDescription
import abandonedstudio.app.tospace.features.about.presentation.AboutScreen
import abandonedstudio.app.tospace.features.app_brief_tts.presentation.AppBriefScreen
import abandonedstudio.app.tospace.features.dashbobard.presentation.DashboardScreen
import abandonedstudio.app.tospace.features.launches.presentation.LaunchesScreen
import abandonedstudio.app.tospace.features.news.presentation.NewsScreen
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

enum class MainDrawerScreen(
    val route: String,
    @StringRes val titleResId: Int,
    val screen: @Composable () -> Unit
) {

    DASHBOARD(
        route = "dashboard",
        titleResId = R.string.main_drawer_dashboard_title,
        screen = { DashboardScreen() }
    ),

    LAUNCHES(
        route = "launches",
        titleResId = R.string.main_drawer_launches_title,
        screen = { LaunchesScreen() }
    ),

    NEWS(
        route = "news",
        titleResId = R.string.main_drawer_news_title,
        screen = { NewsScreen() }
    ),

    ABOUT(
        route = "about",
        titleResId = R.string.about_title,
        screen = { AboutScreen() }
    ),

    APP_BRIEF(
        route = "app_brief",
        titleResId = R.string.app_brief_screen_title,
        screen = { AppBriefScreen() }
    );
}

@Composable
fun MainDrawer() {
    val drawerNavController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalDrawer(
        drawerState = drawerState,
        drawerShape = RectangleShape,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            DrawerContent(drawerNavController, drawerState, scope)
        }
    ) {
        DrawerNavigation(drawerNavController) {
            scope.launch {
                drawerState.open()
            }
        }
    }
}

@Composable
private fun DrawerContent(
    navController: NavController,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    val onItemCLicked: (MainDrawerScreen) -> Unit = {
        navController.navigate(it.route) {
            navController.graph.startDestinationRoute?.also { route ->
                popUpTo(route) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }

        scope.launch {
            drawerState.close()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Brush.verticalGradient(listOf(Color.DarkGray, Color.Black)))
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.spacex_logo),
            contentDescription = contentDescription(),
            modifier = Modifier
                .height(100.dp)
                .padding(horizontal = 16.dp),
            contentScale = ContentScale.Fit
        )

        MainDrawerScreen.values().filter { it.ordinal != MainDrawerScreen.ABOUT.ordinal }.forEach {
            DrawerItem(screen = it, onItemClicked = onItemCLicked)
        }

        Spacer(modifier = Modifier.weight(1f))

        DrawerItem(
            screen = MainDrawerScreen.ABOUT,
            style = MaterialTheme.typography.h5,
            onItemClicked = onItemCLicked
        )
    }
}

@Composable
private fun DrawerItem(
    screen: MainDrawerScreen,
    style: TextStyle = MaterialTheme.typography.h4,
    onItemClicked: (MainDrawerScreen) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClicked(screen) })
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = screen.titleResId),
            style = style,
            color = Color.White
        )
    }
}

@Composable
private fun DrawerNavigation(drawerNavController: NavHostController, onIconClicked: () -> Unit) {
    NavHost(navController = drawerNavController, startDestination = MainDrawerScreen.DASHBOARD.route) {
        MainDrawerScreen.values().forEach {
            composableScreen(it, onIconClicked, it.screen)
        }
    }
}

private fun NavGraphBuilder.composableScreen(screen: MainDrawerScreen, onIconClicked: () -> Unit, content: @Composable () -> Unit) =
    composable(screen.route) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(screen.titleResId, onIconClicked)
            content()
        }
    }

@Composable
private fun TopBar(@StringRes titleResId: Int, onIconClicked: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = titleResId),
                    style = MaterialTheme.typography.h6
                )
            },
            navigationIcon = {
                IconButton(onClick = onIconClicked) {
                    Icon(Icons.Filled.Menu, contentDescription = contentDescription())
                }
            },
            backgroundColor = Color.Transparent,
            contentColor = Color.White,
            elevation = 0.dp
        )
        Divider(color = Color.Blue, thickness = 2.dp)
    }
}