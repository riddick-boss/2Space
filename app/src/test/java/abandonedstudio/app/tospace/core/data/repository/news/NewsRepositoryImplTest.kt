package abandonedstudio.app.tospace.core.data.repository.news

import abandonedstudio.app.tospace.core.data.remote.news.articles.ArticlesRemoteApi
import abandonedstudio.app.tospace.core.data.remote.news.events.EventsRemoteApi
import abandonedstudio.app.tospace.domain.model.news.SpaceArticle
import abandonedstudio.app.tospace.domain.model.news.SpaceEvent
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class NewsRepositoryImplTest {

    private lateinit var repository: NewsRepositoryImpl
    private lateinit var eventsApi: EventsRemoteApi
    private lateinit var articlesApi: ArticlesRemoteApi
    private lateinit var mapper: NewsMapper

    @Before
    fun setUp() {
        eventsApi = mock {
            onBlocking { loadUpcomingEvents() } doReturn mock()
        }
        articlesApi = mock {
            onBlocking { loadArticles(any()) } doReturn mock()
        }
        mapper = mock {
            on { toSpaceEvents(any()) } doReturn listOf(
                SpaceEvent(title = "title1", description = "desc1", imageUrl = null, newsUrl = null, videoUrl = null, type = null)
            )
            on { toSpaceArticles(any()) } doReturn listOf(
                SpaceArticle(title = "title1", summary = "summary1", imageUrl = null, url = null)
            )
        }
        repository = NewsRepositoryImpl(
            eventsApi = eventsApi,
            articlesApi = articlesApi,
            mapper = mapper
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `loadUpcomingEvents calls events api`(): Unit = runBlocking {
        repository.loadUpcomingEvents()
        verify(eventsApi).loadUpcomingEvents()
    }

    @Test
    fun `loadUpcomingEvents calls mapper`(): Unit = runBlocking {
        repository.loadUpcomingEvents()
        verify(mapper).toSpaceEvents(any())
    }

    @Test
    fun `loadArticles calls articles api`(): Unit = runBlocking {
        val numOfArticles = 10
        repository.loadArticles(numOfArticles)
        verify(articlesApi).loadArticles(numOfArticles)
    }

    @Test
    fun `loadArticles calls mapper`(): Unit = runBlocking {
        repository.loadArticles(1)
        verify(mapper).toSpaceArticles(any())
    }
}