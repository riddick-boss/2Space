package abandonedstudio.app.tospace.core.data.repository.launches

import abandonedstudio.app.tospace.core.data.remote.launches.LaunchesRemoteApi
import abandonedstudio.app.tospace.domain.infrastructure.paging.LaunchesPagingSource
import abandonedstudio.app.tospace.domain.model.launches.Launch
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class LaunchesRepositoryImplTest {

    private lateinit var repository: LaunchesRepositoryImpl
    private lateinit var api: LaunchesRemoteApi
    private lateinit var mapper: LaunchesMapper

    @Before
    fun setUp() {
        api = mock {
            onBlocking { loadUpcomingLaunches(any()) } doReturn mock()
            onBlocking { loadUpcomingLaunches(null) } doReturn mock()
        }
        mapper = mock {
            on { toLaunchesPage(any()) } doReturn LaunchesPagingSource.Page(data = listOf(
                Launch("","",Launch.LaunchStatus(0, "", "", ""),Launch.LaunchPad("", ""),"","","",0,0)
            ), next = null)
            on { toDetailedLaunch(any()) } doReturn mock()
        }
        repository = LaunchesRepositoryImpl(
            launchesRemoteApi = api,
            mapper = mapper
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `fetchNextLaunch calls api fetchNextLaunch`(): Unit = runBlocking {
        repository.fetchNextLaunch()
        verify(api).fetchNextLaunch()
    }

    @Test
    fun `fetchPreviousLaunch calls api fetchPreviousLaunch`(): Unit = runBlocking {
        repository.fetchPreviousLaunch()
        verify(api).fetchPreviousLaunch()
    }

    @Test
    fun `loadUpcomingLaunches calls api loadUpcomingLaunches`(): Unit = runBlocking {
        val next = "dummy_next"
        repository.loadUpcomingLaunches(next)
        verify(api).loadUpcomingLaunches(next)
    }

    @Test
    fun `loadUpcomingLaunch calls api loadUpcomingLaunch`(): Unit = runBlocking {
        repository.loadUpcomingLaunch()
        verify(api).loadUpcomingLaunches(null)
    }
}