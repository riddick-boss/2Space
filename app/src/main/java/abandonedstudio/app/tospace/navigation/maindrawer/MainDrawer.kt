package abandonedstudio.app.tospace.navigation.maindrawer

import abandonedstudio.app.tospace.R
import abandonedstudio.app.tospace.core.presentation.util.contentDescription
import abandonedstudio.app.tospace.features.dashbobard.DashboardScreen
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

enum class MainDrawerScreen(val route: String, @StringRes val titleResId: Int) {

    DASHBOARD(
        route = "dashboard",
        titleResId = R.string.main_drawer_dashboard_title
    )
}

@Composable
fun MainDrawer() {
    val drawerNavController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalDrawer(
        drawerState = drawerState,
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
private fun DrawerContent(navController: NavController, drawerState: DrawerState, scope: CoroutineScope) {
    Column(
        modifier = Modifier
            .background(Brush.verticalGradient(listOf(Color.DarkGray, Color.Blue)))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // TODO: icon
            contentDescription = contentDescription(),
            modifier = Modifier.height(100.dp)
        )

        MainDrawerScreen.values().forEach {
            DrawerItem(screen = it) {
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
        }
    }
}

@Composable
private fun DrawerItem(screen: MainDrawerScreen, onIconClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onIconClicked)
    ) {
        Text(
            text = stringResource(id = screen.titleResId),
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
private fun DrawerNavigation(drawerNavController: NavHostController, onIconClicked: () -> Unit) {
    NavHost(navController = drawerNavController, startDestination = MainDrawerScreen.DASHBOARD.route) {
        composableScreen(MainDrawerScreen.DASHBOARD, onIconClicked) {
            DashboardScreen()
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
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = titleResId),
                style = MaterialTheme.typography.subtitle2
            )
        },
        navigationIcon = {
            IconButton(onClick = onIconClicked) {
                Icon(Icons.Filled.Menu, contentDescription = contentDescription())
            }
        },
        backgroundColor = Color.Transparent,
        contentColor = Color.White
    )
}