package abandonedstudio.app.tospace.features.news.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel()
) {
    val tabs = Tab.values().toList()

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        Tabs(tabs = tabs, selectedIndex = pagerState.currentPage) {
            coroutineScope.launch {
                pagerState.scrollToPage(it)
            }
        }

        Pager(pagerState = pagerState, pagesCount = tabs.size) {
            tabs[it].content(viewModel)
        }
    }
}

@Composable
private fun Tabs(
    tabs: List<Tab>,
    selectedIndex: Int,
    onSelected: (index: Int) -> Unit
) {
    TabRow(
        selectedTabIndex = selectedIndex,
        backgroundColor = Color.Transparent,
        indicator = {
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(it[selectedIndex]),
                color = Color.Blue
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            val isSelected = selectedIndex == index
            Tab(
                text = {
                    Text(
                        text = stringResource(tab.titleResId),
                        color = Color.White,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                },
                selected = isSelected,
                onClick = { onSelected.invoke(index) }
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
private fun Pager(
    pagerState: PagerState,
    pagesCount: Int,
    content: @Composable (page: Int) -> Unit
) {
    HorizontalPager(
        state = pagerState,
        count = pagesCount
    ) { page ->
        Box(modifier = Modifier.fillMaxSize()) {
            content.invoke(page)
        }
    }
}