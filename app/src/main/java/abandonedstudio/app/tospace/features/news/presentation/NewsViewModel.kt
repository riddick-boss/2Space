package abandonedstudio.app.tospace.features.news.presentation

import abandonedstudio.app.tospace.core.domain.model.util.Result
import abandonedstudio.app.tospace.core.domain.util.extension.showToast
import abandonedstudio.app.tospace.core.domain.util.extension.toMessage
import abandonedstudio.app.tospace.features.news.data.Article
import abandonedstudio.app.tospace.features.news.data.Event
import abandonedstudio.app.tospace.features.news.domain.DataSource
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    application: Application,
    private val dataSource: DataSource
) : AndroidViewModel(application) {

//    events

    private val upcomingEventsRefreshFlow = MutableSharedFlow<Unit>()

    fun onUpcomingEventsRefresh() {
        viewModelScope.launch {
            upcomingEventsRefreshFlow.emit(Unit)
        }
    }

    val upcomingEventsFlow: StateFlow<Result<List<Event>>?> by lazy {
        upcomingEventsRefreshFlow
            .onStart { emit(Unit) }
            .flatMapLatest {
                flow {
                    emit(null)
                    try {
                        emit(Result.Success(dataSource.loadUpcomingEvents()))
                    } catch (e: Exception) {
                        e.toMessage()?.run {
                            showToast(this)
                        }
                        emit(Result.Failure(e))
                    }
                }
            }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
    }

//    articles

    private val articlesRefreshFlow = MutableSharedFlow<Unit>()

    fun onArticlesRefresh() {
        viewModelScope.launch {
            articlesRefreshFlow.emit(Unit)
        }
    }

    val articles: StateFlow<Result<List<Article>>?> by lazy {
        articlesRefreshFlow
            .onStart { emit(Unit) }
            .flatMapLatest {
                flow {
                    emit(null)
                    try {
                        emit(Result.Success(dataSource.loadArticles()))
                    } catch (e: Exception) {
                        e.toMessage()?.run {
                            showToast(this)
                        }
                        emit(Result.Failure(e))
                    }
                }
            }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
    }
}